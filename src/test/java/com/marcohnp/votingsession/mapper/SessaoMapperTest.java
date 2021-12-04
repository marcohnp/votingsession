package com.marcohnp.votingsession.mapper;

import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.stub.SessaoStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessaoMapperTest {

    @Test
    void requestToModel_deveMapearRequestParaModel() {
        var model = SessaoModel.builder().duracao(5).build();

        assertEquals(model, SessaoMapper.requestToModel(SessaoStub.createSessaoRequestStub()));
    }

    @Test
    void modelToEntity_deveMapearModelParaEntity() {
        assertEquals(SessaoStub.createSessaoEntityStub(),
                SessaoMapper.modelToEntity(SessaoStub.createSessaoModelStub()));
    }

    @Test
    void entityToModel_deveMapearEntityParaModel() {
        assertEquals(SessaoStub.createSessaoModelStub(),
                SessaoMapper.entityToModel(SessaoStub.createSessaoEntityStub()));
    }

    @Test
    void modelToResponse_deveMapearModelParaResponse() {
        assertEquals(SessaoStub.createSessaoResponseStub(),
                SessaoMapper.modelToResponse(SessaoStub.createSessaoModelStub()));
    }

    @Test
    void resultadoModelToResponse_deveMapearResultadoModelParaResponse() {
        assertEquals(SessaoStub.createSessaoResultadoResponseStub(),
                SessaoMapper.resultadoModelToResponse(SessaoStub.createSessaoResultadoModelStub()));
    }

    @Test
    void modelToResultadoModel_deveMapearModelParaResultadoModel() {
        var resultadoModel = SessaoStub.createSessaoResultadoModelStub();
        resultadoModel.setResultadoVotacao(null);
        assertEquals(resultadoModel, SessaoMapper.modelToResultadoModel(SessaoStub.createSessaoModelStub()));
    }

}