package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.mapper.SessaoMapper;
import com.marcohnp.votingsession.model.request.SessaoRequest;
import com.marcohnp.votingsession.model.response.SessaoResponse;
import com.marcohnp.votingsession.model.response.SessaoResultadoResponse;
import com.marcohnp.votingsession.service.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SessaoFacade {

    private SessaoService service;

    public SessaoResponse criarSessaoParaPauta(SessaoRequest request, String idPauta) {
        return SessaoMapper.modelToResponse(service.criarSessaoParaPauta(SessaoMapper.requestToModel(request), idPauta));
    }

    public SessaoResponse recuperarSessaoPorId(String id) {
        return SessaoMapper.modelToResponse(service.recuperarSessaoPorId(id));
    }

    public String finalizarSessao(String id) {
        return service.finalizarSessaoManualmente(id);
    }

    public SessaoResultadoResponse recuperarResultadoSessao(String id) {
        return SessaoMapper.resultadoModelToResponse(service.recuperarResultadoSessao(id));
    }
}
