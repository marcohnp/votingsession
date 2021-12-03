package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.exception.handler.Handler;
import com.marcohnp.votingsession.facade.SessaoFacade;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.request.SessaoRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import com.marcohnp.votingsession.model.response.SessaoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/sessoes")
public class SessaoController {

    private SessaoFacade facade;

    @PostMapping("/{idPauta}")
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoResponse criarSessaoParaPauta(@Valid @RequestBody SessaoRequest request, @PathVariable String idPauta) {
        return facade.criarSessaoParaPauta(request, idPauta);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SessaoResponse recuperarSessaoPorId(@PathVariable String id) {
        return facade.recuperarSessaoPorId(id);
    }
}
