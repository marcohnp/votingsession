package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.mapper.PautaMapper;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import com.marcohnp.votingsession.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PautaFacade {

    private PautaService service;

    public PautaResponse criarPautaEmSessao(PautaRequest request) {
        return PautaMapper.modelToResponse(service.criarPautaEmSessao(PautaMapper.requestToModel(request)));
    }
}
