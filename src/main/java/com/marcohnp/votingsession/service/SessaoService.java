package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.CpfNaoHabilitadoParaVotarException;
import com.marcohnp.votingsession.exception.exceptions.SessaoEncerradaException;
import com.marcohnp.votingsession.exception.exceptions.SessaoNotFoundException;
import com.marcohnp.votingsession.mapper.SessaoMapper;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.model.VotoModel;
import com.marcohnp.votingsession.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class SessaoService {


    private PautaService pautaService;
    private SessaoRepository repository;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

    public SessaoModel criarSessaoParaPauta(SessaoModel model, String idPauta) {
        model.setIdPauta(idPauta);
        model.setSessaoAberta(Boolean.TRUE);
        var sessao = SessaoMapper.entityToModel(repository.save(SessaoMapper.modelToEntity(model)));
        pautaService.salvarSessaoEmPauta(sessao);
        log.info("Sessão id {} aberta para votação.", sessao.getId());
        schedule(sessao);
        return sessao;
    }

    private void schedule(SessaoModel sessaoModel) {
        executor.schedule(() -> {
            var sessaoAtualizada = repository.findById(sessaoModel.getId()).orElseThrow(() -> {
                log.error("Sessão com id {} não encontrada", sessaoModel.getId());
                throw new SessaoNotFoundException("Sessao não encontada");
            });
            sessaoAtualizada.setSessaoAberta(Boolean.FALSE);
            sessaoAtualizada.setDataEncerramentoSessao(LocalDateTime.now());
            log.info("Sessão id {} encerrada.", sessaoAtualizada.getId());
            repository.save(sessaoAtualizada);
        }, sessaoModel.getDuracao(), TimeUnit.MINUTES);
    }

    public SessaoModel recuperarSessaoPorId(String id) {
        return SessaoMapper.entityToModel(repository.findById(id).orElseThrow(() -> {
            log.error("Sessão com id {} não encontrada", id);
            throw new SessaoNotFoundException("Sessao não encontada");
        }));
    }

    public void salvarVotoNaSessao(VotoModel model) {
        var sessao = SessaoMapper.entityToModel(repository.findById(model.getIdSessao()).orElseThrow(() -> {
            log.error("Sessão com id {} não encontrada", model.getIdSessao());
            throw new SessaoNotFoundException("Sessao não encontada");
        }));
        validarSessao(model, sessao);
        repository.save(SessaoMapper.modelToEntity(sessao));
    }

    private void validarSessao(VotoModel model, SessaoModel sessao) {
        if (sessao.getSessaoAberta()) {
            log.info("Sessão id {} continua aberta e apta para receber votos.", sessao.getId());
            addVoto(model, sessao);
            addCpf(model, sessao);
        } else {
            log.error("Sessão id {} está encerrada e não pode receber mais votos.", sessao.getId());
            throw new SessaoEncerradaException("Sessão encerrada e não pode mais receber votos.");
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
            if(votoModel.getCpf().equals(model.getCpf())) {
                log.error("CPF {} já votou na sessão informada.", model.getCpf());
                throw new CpfNaoHabilitadoParaVotarException("CPF informado já votou nessa sessão.");
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
}
