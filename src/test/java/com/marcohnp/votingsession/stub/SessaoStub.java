package com.marcohnp.votingsession.stub;

import com.marcohnp.votingsession.entity.SessaoEntity;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.model.SessaoResultadoModel;
import com.marcohnp.votingsession.model.request.SessaoRequest;
import com.marcohnp.votingsession.model.response.SessaoResponse;
import com.marcohnp.votingsession.model.response.SessaoResultadoResponse;

import java.time.LocalDateTime;
import java.util.List;

public class SessaoStub {

    public static SessaoRequest createSessaoRequestStub() {
        return SessaoRequest.builder()
                .duracao(5)
                .build();
    }

    public static SessaoModel createSessaoModelStub() {
        return SessaoModel.builder()
                .id("61abd13362ecab4fc1246cae")
                .duracao(5)
                .dataInicioSessao(LocalDateTime.of(2021, 5, 1, 18, 30, 0))
                .dataEncerramentoSessao(LocalDateTime.of(2021, 5, 1, 18, 35, 0))
                .cpfsVotantes(List.of("22714037070", "74568706084"))
                .votos(VotoStub.createListVotoModelStub())
                .sessaoAberta(Boolean.FALSE)
                .idPauta("61abd13362ecab4fc1246cae")
                .build();
    }

    public static SessaoResponse createSessaoResponseStub() {
        return SessaoResponse.builder()
                .id("61abd13362ecab4fc1246cae")
                .duracao(5)
                .dataInicioSessao(LocalDateTime.of(2021, 5, 1, 18, 30, 0))
                .dataEncerramentoSessao(LocalDateTime.of(2021, 5, 1, 18, 35, 0))
                .cpfsVotantes(List.of("22714037070", "74568706084"))
                .votos(VotoStub.createListVotoResponseStub())
                .sessaoAberta(Boolean.FALSE)
                .idPauta("61abd13362ecab4fc1246cae")
                .build();
    }

    public static SessaoEntity createSessaoEntityStub() {
        return SessaoEntity.builder()
                .id("61abd13362ecab4fc1246cae")
                .duracao(5)
                .dataInicioSessao(LocalDateTime.of(2021, 5, 1, 18, 30, 0))
                .dataEncerramentoSessao(LocalDateTime.of(2021, 5, 1, 18, 35, 0))
                .cpfsVotantes(List.of("22714037070", "74568706084"))
                .votos(VotoStub.createListVotoEntityStub())
                .sessaoAberta(Boolean.FALSE)
                .idPauta("61abd13362ecab4fc1246cae")
                .build();
    }

    public static SessaoResultadoModel createSessaoResultadoModelStub() {
        return SessaoResultadoModel.builder()
                .id("61abd13362ecab4fc1246cae")
                .dataInicioSessao(LocalDateTime.of(2021, 5, 1, 18, 30, 0))
                .dataEncerramentoSessao(LocalDateTime.of(2021, 5, 1, 18, 35, 0))
                .cpfsVotantes(List.of("22714037070", "74568706084"))
                .resultadoVotacao(VotoStub.createVotoResultadoModelStub())
                .idPauta("61abd13362ecab4fc1246cae")
                .build();
    }

    public static SessaoResultadoResponse createSessaoResultadoResponseStub() {
        return SessaoResultadoResponse.builder()
                .id("61abd13362ecab4fc1246cae")
                .dataInicioSessao(LocalDateTime.of(2021, 5, 1, 18, 30, 0))
                .dataEncerramentoSessao(LocalDateTime.of(2021, 5, 1, 18, 35, 0))
                .cpfsVotantes(List.of("22714037070", "74568706084"))
                .resultadoVotacao(VotoStub.createVotoResultadoResponseStub())
                .idPauta("61abd13362ecab4fc1246cae")
                .build();
    }
}
