package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.mapper.PautaMapper;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import com.marcohnp.votingsession.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PautaFacade {

    private PautaService service;

    public PautaResponse criarPauta(PautaRequest request) {
        return PautaMapper.modelToResponse(service.criarPauta(PautaMapper.requestToModel(request)));
    }

    public List<PautaResponse> listarPautas() {
        return service.listarPautas().stream().map(PautaMapper::modelToResponse).collect(Collectors.toList());
    }

    public PautaResponse recuperarPautaPorId(String id) {
        return PautaMapper.modelToResponse(service.recuperarPautaPorId(id));
    }
}
