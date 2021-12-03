package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.PautaNotFoundException;
import com.marcohnp.votingsession.mapper.PautaMapper;
import com.marcohnp.votingsession.model.PautaModel;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.repository.PautaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PautaService {

    private PautaRepository pautaRepository;

    public PautaModel criarPautaEmSessao(PautaModel model) {
        return PautaMapper.entityToModel(pautaRepository.save(PautaMapper.modelToEntity(model)));
    }

    public void salvarSessaoEmPauta(SessaoModel sessao) {
        var pauta = PautaMapper.entityToModel(pautaRepository.findById(sessao.getIdPauta()).orElseThrow(() -> {
            log.error("Sessao com id {} não encontada.",sessao.getIdPauta());
            throw new PautaNotFoundException("Pauta não encontada");
        }));
        pauta.setSessao(sessao);
        pautaRepository.save(PautaMapper.modelToEntity(pauta));
    }
}
