package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.entity.SessaoEntity;
import com.marcohnp.votingsession.enums.VotoEnum;
import com.marcohnp.votingsession.exception.exceptions.CpfNaoHabilitadoParaVotarException;
import com.marcohnp.votingsession.exception.exceptions.SessaoEncerradaException;
import com.marcohnp.votingsession.exception.exceptions.SessaoNotFoundException;
import com.marcohnp.votingsession.kafka.producer.SessaoKafkaProducer;
import com.marcohnp.votingsession.mapper.SessaoMapper;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.model.SessaoResultadoModel;
import com.marcohnp.votingsession.model.VotoModel;
import com.marcohnp.votingsession.model.VotoResultadoModel;
import com.marcohnp.votingsession.provider.DateTimeProvider;
import com.marcohnp.votingsession.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@AllArgsConstructor
public class SessaoService {


    private PautaService pautaService;
    private SessaoRepository repository;
    private DateTimeProvider provider;
    private SessaoKafkaProducer sessaoKafkaProducer;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

    @EventListener(ApplicationReadyEvent.class)
    public void gerarScheduleSessoesAbertas() {
        var sessoesAbertas = repository.findAll()
                .stream()
                .filter(sessao -> TRUE.equals(sessao.getSessaoAberta()))
                .map(SessaoMapper::entityToModel)
                .collect(Collectors.toList());

        if (ObjectUtils.isNotEmpty(sessoesAbertas)) {
            sessoesAbertas.forEach(sessao -> {
                log.info("Sessao id {} continua aberta.", sessao.getId());
                this.schedule(sessao);
            });
        }
    }

    public SessaoModel criarSessaoParaPauta(SessaoModel model, String idPauta) {
        model.setIdPauta(idPauta);
        model.setDataInicioSessao(provider.now());
        model.setSessaoAberta(TRUE);
        var sessao = SessaoMapper.entityToModel(repository.save(SessaoMapper.modelToEntity(model)));
        pautaService.salvarSessaoEmPauta(sessao);
        log.info("Sess??o id {} aberta para vota????o.", sessao.getId());
        schedule(sessao);
        return sessao;
    }

    private void schedule(SessaoModel sessaoModel) {
        LocalDateTime horarioAtual = provider.now();
        LocalDateTime horarioFechamento = sessaoModel.getDataInicioSessao().plus(sessaoModel.getDuracao(), ChronoUnit.MINUTES);
        if (horarioAtual.isBefore(horarioFechamento)) {
            executor.schedule(() -> {
                var sessaoAtualizada = repository.findById(sessaoModel.getId()).orElseThrow(() -> {
                    log.error("Sess??o com id {} n??o encontrada", sessaoModel.getId());
                    throw new SessaoNotFoundException("Sessao n??o encontada");
                });
                encerrarSessao(sessaoAtualizada);
            }, ChronoUnit.MINUTES.between(horarioAtual, horarioFechamento), TimeUnit.MINUTES);
        } else {
            var sessaoAtualizada = repository.findById(sessaoModel.getId()).orElseThrow(() -> {
                log.error("Sess??o com id {} n??o encontrada", sessaoModel.getId());
                throw new SessaoNotFoundException("Sessao n??o encontada");
            });
            encerrarSessao(sessaoAtualizada);
        }
    }

    private void encerrarSessao(SessaoEntity sessaoAtualizada) {
        if (sessaoAtualizada.getSessaoAberta()) {
            sessaoAtualizada.setSessaoAberta(Boolean.FALSE);
            sessaoAtualizada.setDataEncerramentoSessao(provider.now());
            repository.save(sessaoAtualizada);
            pautaService.salvarSessaoEmPauta(SessaoMapper.entityToModel(sessaoAtualizada));
            log.info("Sess??o id {} encerrada.", sessaoAtualizada.getId());
            var resultadoSessao = SessaoMapper.resultadoModelToKafka(recuperarResultadoSessao(sessaoAtualizada.getId()));
            sessaoKafkaProducer.publish(resultadoSessao);
            log.info("Resultado sess??o id {} publicado no Kafka.", sessaoAtualizada.getId());
        } else {
            log.info("Sess??o id {} foi encerrada manualmente", sessaoAtualizada.getId());
        }
    }

    public String finalizarSessaoManualmente(String id) {
        var sessao = repository.findById(id).orElseThrow(() -> {
            log.error("Sess??o com id {} n??o encontrada", id);
            throw new SessaoNotFoundException("Sessao n??o encontada");
        });
        sessao.setSessaoAberta(Boolean.FALSE);
        sessao.setDataEncerramentoSessao(provider.now());
        repository.save(sessao);
        pautaService.salvarSessaoEmPauta(SessaoMapper.entityToModel(sessao));
        var resultadoSessao = SessaoMapper.resultadoModelToKafka(recuperarResultadoSessao(id));
        sessaoKafkaProducer.publish(resultadoSessao);
        log.info("Sess??o id {} encerrada.", id);
        log.info("Resultado sess??o id {} publicado no Kafka.", id);
        return "Sess??o " + id + " encerrada.";
    }

    public SessaoModel recuperarSessaoPorId(String id) {
        return SessaoMapper.entityToModel(repository.findById(id).orElseThrow(() -> {
            log.error("Sess??o com id {} n??o encontrada", id);
            throw new SessaoNotFoundException("Sessao n??o encontada");
        }));
    }

    public void salvarVotoNaSessao(VotoModel model) {
        var sessao = SessaoMapper.entityToModel(repository.findById(model.getIdSessao()).orElseThrow(() -> {
            log.error("Sess??o com id {} n??o encontrada", model.getIdSessao());
            throw new SessaoNotFoundException("Sessao n??o encontada");
        }));
        validarSessao(model, sessao);
        repository.save(SessaoMapper.modelToEntity(sessao));
    }

    private void validarSessao(VotoModel model, SessaoModel sessao) {
        if (sessao.getSessaoAberta()) {
            log.info("Sess??o id {} continua aberta e apta para receber votos.", sessao.getId());
            addVoto(model, sessao);
            addCpf(model, sessao);
        } else {
            log.error("Sess??o id {} est?? encerrada e n??o pode receber mais votos.", sessao.getId());
            throw new SessaoEncerradaException("Sess??o encerrada e n??o pode mais receber votos.");
        }
    }

    private void addVoto(VotoModel model, SessaoModel sessao) {
        if (ObjectUtils.isNotEmpty(sessao.getVotos())) {
            var listaVotos = sessao.getVotos();
            validarCpfVotante(model, listaVotos);
            listaVotos.add(model);
            sessao.setVotos(listaVotos);
        } else {
            var novaListaVotos = new ArrayList<VotoModel>();
            novaListaVotos.add(model);
            sessao.setVotos(novaListaVotos);
        }
    }

    private void validarCpfVotante(VotoModel model, List<VotoModel> listaVotos) {
        listaVotos.forEach(votoModel -> {
            if (votoModel.getCpf().equals(model.getCpf())) {
                log.error("CPF {} j?? votou na sess??o informada.", model.getCpf());
                throw new CpfNaoHabilitadoParaVotarException("CPF informado j?? votou nessa sess??o.");
            }
        });
    }

    private void addCpf(VotoModel model, SessaoModel sessao) {
        if (ObjectUtils.isNotEmpty(sessao.getCpfsVotantes())) {
            var listaCpf = sessao.getCpfsVotantes();
            listaCpf.add(model.getCpf());
            sessao.setCpfsVotantes(listaCpf);
        } else {
            var novaListaCpf = new ArrayList<String>();
            novaListaCpf.add(model.getCpf());
            sessao.setCpfsVotantes(novaListaCpf);
        }
    }

    public SessaoResultadoModel recuperarResultadoSessao(String id) {
        var sessao = SessaoMapper.entityToModel(repository.findById(id).orElseThrow(() -> {
            log.error("Sess??o com id {} n??o encontrada", id);
            throw new SessaoNotFoundException("Sessao n??o encontada");
        }));
        log.info("Iniciado processo para recuperar resultado da Sess??o id {}", id);
        var resultadoSessao = SessaoMapper.modelToResultadoModel(sessao);
        contarVotos(sessao, resultadoSessao);
        return resultadoSessao;
    }

    private void contarVotos(SessaoModel sessao, SessaoResultadoModel resultadoSessao) {
        var votosSim = (int) sessao.getVotos().stream()
                .filter(votoModel -> votoModel.getVoto().equals(VotoEnum.SIM)).count();
        var votosNao = (int) sessao.getVotos().stream()
                .filter(votoModel -> votoModel.getVoto().equals(VotoEnum.NAO)).count();
        resultadoSessao.setResultadoVotacao(
                VotoResultadoModel.builder()
                        .sim(votosSim)
                        .nao(votosNao)
                        .build());
        log.info("Votos contados com sucesso. Sess??o id {} : Sim({}), N??o({})", resultadoSessao.getId(), votosSim, votosNao);
    }
}
