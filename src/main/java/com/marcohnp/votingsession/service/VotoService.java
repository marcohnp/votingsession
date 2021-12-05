package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.CpfNaoHabilitadoParaVotarException;
import com.marcohnp.votingsession.integration.CpfValidatorIntegration;
import com.marcohnp.votingsession.mapper.VotoMapper;
import com.marcohnp.votingsession.model.VotoModel;
import com.marcohnp.votingsession.provider.DateTimeProvider;
import com.marcohnp.votingsession.repository.VotoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class VotoService {

    private SessaoService sessaoService;
    private CpfValidatorIntegration integration;
    private DateTimeProvider provider;
    private VotoRepository repository;

    public VotoModel enviarVotoParaSessao(VotoModel model, String idSessao) {
        validarCpf(model);
        var votoSalvo = salvarVoto(model, idSessao);
        sessaoService.salvarVotoNaSessao(votoSalvo);
        return votoSalvo;
    }

    private VotoModel salvarVoto(VotoModel model, String idSessao) {
        model.setDataVoto(provider.now());
        model.setIdSessao(idSessao);
        return VotoMapper.entityToModel(repository.save(VotoMapper.modelToEntity(model)));
    }

    private void validarCpf(VotoModel model) {
        var response = integration.validarCpf(model.getCpf());
        if ("ABLE_TO_VOTE".equals(response.getStatus())) {
            log.info("CPF {} está habilidatado para votar", model.getCpf());
        } else {
            log.error("CPF {} não está habilitado para votar", model.getCpf());
            throw new CpfNaoHabilitadoParaVotarException("CPF informado não está habilitado para votar");
        }
    }
}
