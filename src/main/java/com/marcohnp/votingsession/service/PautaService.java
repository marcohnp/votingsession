package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.PautaNotFoundException;
import com.marcohnp.votingsession.mapper.PautaMapper;
import com.marcohnp.votingsession.model.PautaModel;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.repository.PautaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PautaService {

    private PautaRepository repository;

    public PautaModel criarPauta(PautaModel model) {
        return PautaMapper.entityToModel(repository.save(PautaMapper.modelToEntity(model)));
    }

    public void salvarSessaoEmPauta(SessaoModel sessao) {
        var pauta = PautaMapper.entityToModel(repository.findById(sessao.getIdPauta()).orElseThrow(() -> {
            log.error("Sessao com id {} não encontada.", sessao.getIdPauta());
            throw new PautaNotFoundException("Pauta não encontada");
        }));
        pauta.setSessao(sessao);
        repository.save(PautaMapper.modelToEntity(pauta));
    }

    public List<PautaModel> listarPautas() {
        return repository.findAll().stream().map(PautaMapper::entityToModel).collect(Collectors.toList());
    }

    public PautaModel recuperarPautaPorId(String id) {
        return PautaMapper.entityToModel(repository.findById(id).orElseThrow(() -> {
            log.error("Sessao com id {} não encontada.", id);
            throw new PautaNotFoundException("Pauta não encontada");
        }));
    }
}
