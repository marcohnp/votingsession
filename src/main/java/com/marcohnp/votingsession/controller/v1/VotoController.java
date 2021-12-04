package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.facade.VotoFacade;
import com.marcohnp.votingsession.model.request.VotoRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import com.marcohnp.votingsession.model.response.VotoResponse;
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
@RequestMapping(value = "/v1/votos")
public class VotoController {

    private VotoFacade facade;

    @ApiOperation(value = "Enviar voto para sessão e salvar no banco.")
    @PostMapping("/{idSessao}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = PautaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public VotoResponse enviarVotoParaSessao(@Valid @RequestBody VotoRequest request, @PathVariable String idSessao) {
        log.info("Efetuando chamada REST (enviarVotoParaSessao), método POST para '/v1/votos/{}'. Request: {}", idSessao, request);
        return facade.enviarVotoParaSessao(request, idSessao);
    }
}
