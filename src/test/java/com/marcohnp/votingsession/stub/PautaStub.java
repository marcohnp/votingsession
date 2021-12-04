package com.marcohnp.votingsession.stub;

import com.marcohnp.votingsession.entity.PautaEntity;
import com.marcohnp.votingsession.model.PautaModel;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;

public class PautaStub {

    public static PautaRequest createPautaRequestStub() {
        return PautaRequest.builder()
                .pauta("Pauta Votação Estacionamento")
                .build();
    }

    public static PautaModel createPautaModelStub() {
        return PautaModel.builder()
                .id("61abd13362ecab4fc1246cae")
                .pauta("Pauta Votação Estacionamento")
                .sessao(SessaoStub.createSessaoModelStub())
                .build();
    }

    public static PautaResponse createPautaResponseStub() {
        return PautaResponse.builder()
                .id("61abd13362ecab4fc1246cae")
                .pauta("Pauta Votação Estacionamento")
                .sessao(SessaoStub.createSessaoResponseStub())
                .build();
    }

    public static PautaEntity createPautaEntityStub() {
        return PautaEntity.builder()
                .id("61abd13362ecab4fc1246cae")
                .pauta("Pauta Votação Estacionamento")
                .sessao(SessaoStub.createSessaoEntityStub())
                .build();
    }
}
