package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.facade.VotoFacade;
import com.marcohnp.votingsession.model.request.VotoRequest;
import com.marcohnp.votingsession.model.response.VotoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/votos")
public class VotoController {

    private VotoFacade facade;

    @PostMapping("/{idSessao}")
    @ResponseStatus(HttpStatus.CREATED)
    public VotoResponse enviarVotoParaSessao(@Valid @RequestBody VotoRequest request, @PathVariable String idSessao) {
        return facade.enviarVotoParaSessao(request, idSessao);
    }
}
