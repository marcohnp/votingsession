package com.marcohnp.votingsession.mapper;

import com.marcohnp.votingsession.model.PautaModel;
import com.marcohnp.votingsession.stub.PautaStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PautaMapperTest {

    @Test
    void requestToModel_deveMapearRequestParaModel() {
        var model = PautaModel.builder().pauta("Pauta Votação Estacionamento").build();

        assertEquals(model, PautaMapper.requestToModel(PautaStub.createPautaRequestStub()));
    }

    @Test
    void modelToEntity_deveMapearModelParaEntity() {
        assertEquals(PautaStub.createPautaEntityStub(),
                PautaMapper.modelToEntity(PautaStub.createPautaModelStub()));
    }

    @Test
    void entityToModel_deveMapearEntityParaModel() {
        assertEquals(PautaStub.createPautaModelStub(),
                PautaMapper.entityToModel(PautaStub.createPautaEntityStub()));
    }

    @Test
    void modelToResponse_deveMapearModelParaResponse() {
        assertEquals(PautaStub.createPautaResponseStub(),
                PautaMapper.modelToResponse(PautaStub.createPautaModelStub()));
    }

}