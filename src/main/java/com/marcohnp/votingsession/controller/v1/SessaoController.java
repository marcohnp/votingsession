package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.exception.exceptions.SessaoNotFoundException;
import com.marcohnp.votingsession.facade.SessaoFacade;
import com.marcohnp.votingsession.model.request.SessaoRequest;
import com.marcohnp.votingsession.model.response.SessaoResponse;
import com.marcohnp.votingsession.model.response.SessaoResultadoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Voting Session")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/sessoes")
public class SessaoController {

    private SessaoFacade facade;

    @ApiOperation(value = "Criar sessao para pauta informada.")
    @PostMapping("/{idPauta}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = SessaoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public SessaoResponse criarSessaoParaPauta(@Valid @RequestBody SessaoRequest request, @PathVariable String idPauta) {
        log.info("Efetuando chamada REST (criarSessaoParaPauta), método POST para '/v1/sessoes/{}'. Request: {}", idPauta, request);
        return facade.criarSessaoParaPauta(request, idPauta);
    }

    @ApiOperation(value = "Recuparar sessao do banco de dados conforme id informado.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = SessaoResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = SessaoNotFoundException.class)
    })
    public SessaoResponse recuperarSessaoPorId(@PathVariable String id) {
        log.info("Efetuando chamada REST (recuperarSessaoPorId), método GET para '/v1/sessoes/{}'.", id);
        return facade.recuperarSessaoPorId(id);
    }

    @ApiOperation(value = "Finaliza sessao setando flag sessaoAberta para false.")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = String.class),
            @ApiResponse(code = 404, message = "Not Found", response = SessaoNotFoundException.class)
    })
    public String finalizarSessao(@PathVariable String id) {
        log.info("Efetuando chamada REST (finalizarSessao), método PATCH para '/v1/sessoes/{}'.", id);
        return facade.finalizarSessao(id);
    }

    @ApiOperation(value = "Recupera resultado da votação durante a sessão informada.")
    @GetMapping("/resultado/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = SessaoResultadoResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = SessaoNotFoundException.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public SessaoResultadoResponse recuperarResultadoSessao(@PathVariable String id) {
        log.info("Efetuando chamada REST (recuperarResultadoSessao), método GET para '/v1/sessoes/resultado/{}'.", id);
        return facade.recuperarResultadoSessao(id);
    }
}
