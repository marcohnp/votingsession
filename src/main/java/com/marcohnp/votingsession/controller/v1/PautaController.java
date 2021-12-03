package com.marcohnp.votingsession.controller.v1;

import com.marcohnp.votingsession.facade.PautaFacade;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PautaResponse> listarPautas() {
        return facade.listarPautas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PautaResponse recuperarPautaPorId(@PathVariable String id) {
        return facade.recuperarPautaPorId(id);
    }
}
