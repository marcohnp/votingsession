package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.exception.exceptions.PautaNotFoundException;
import com.marcohnp.votingsession.facade.PautaFacade;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Voting Session")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/pautas")
public class PautaController {

    private PautaFacade facade;

    @ApiOperation(value = "Criar pauta no banco de dados.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = PautaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public PautaResponse criarPauta(@Valid @RequestBody PautaRequest request) {
        log.info("Efetuando chamada REST (criarPauta), método POST para '/v1/pautas'. Request: {}", request);
        return facade.criarPauta(request);
    }

    @ApiOperation(value = "Listar pautas existentes no banco de dados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PautaResponse.class),
    })
    public List<PautaResponse> listarPautas() {
        log.info("Efetuando chamada REST (listarPautas), método GET para '/v1/pautas'.");
        return facade.listarPautas();
    }

    @ApiOperation(value = "Recuperar pauta do banco de dados conforme id informado.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PautaResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = PautaNotFoundException.class)
    })
    public PautaResponse recuperarPautaPorId(@PathVariable String id) {
        log.info("Efetuando chamada REST (recuperarPautaPorId), método GET para '/v1/pautas/{}'.", id);
        return facade.recuperarPautaPorId(id);
    }
}
