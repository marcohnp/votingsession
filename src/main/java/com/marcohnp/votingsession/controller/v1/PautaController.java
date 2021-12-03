package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.exception.exceptions.RequestInvalidaException;
import com.marcohnp.votingsession.exception.handler.Handler;
import com.marcohnp.votingsession.facade.PautaFacade;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/pautas")
public class PautaController {

    private PautaFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaResponse criarPautaEmSessao(@Valid @RequestBody PautaRequest request) {
        return facade.criarPautaEmSessao(request);
    }
}
