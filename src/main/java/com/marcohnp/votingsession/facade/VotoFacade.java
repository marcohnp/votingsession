package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.mapper.VotoMapper;
import com.marcohnp.votingsession.model.request.VotoRequest;
import com.marcohnp.votingsession.model.response.VotoResponse;
import com.marcohnp.votingsession.service.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VotoFacade {

    private VotoService service;

    public VotoResponse enviarVotoParaSessao(VotoRequest request, String idSessao) {
        return VotoMapper.modelToResponse(service.enviarVotoParaSessao(VotoMapper.requestToModel(request), idSessao));
    }
}
