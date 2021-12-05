package com.marcohnp.votingsession.mapper;

import com.marcohnp.votingsession.enums.VotoEnum;
import com.marcohnp.votingsession.model.VotoModel;
import com.marcohnp.votingsession.stub.VotoStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VotoMapperTest {

    @Test
    void requestToModel_deveMapearRequestParaModel() {
        var model = VotoModel.builder()
                .voto(VotoEnum.SIM)
                .cpf("22714037070")
                .build();

        assertEquals(model, VotoMapper.requestToModel(VotoStub.createVotoRequestSimStub()));
    }

    @Test
    void modelToEntity_deveMapearModelParaEntity() {
        assertEquals(VotoStub.createVotoEntitySimStub(),
                VotoMapper.modelToEntity(VotoStub.createVotoModelSimStub()));
    }

    @Test
    void entityToModel_deveMapearEntityParaModel() {
        assertEquals(VotoStub.createVotoModelSimStub(),
                VotoMapper.entityToModel(VotoStub.createVotoEntitySimStub()));
    }

    @Test
    void modelToResponse_deveMapearModelParaResponse() {
        assertEquals(VotoStub.createVotoResponseSimStub(),
                VotoMapper.modelToResponse(VotoStub.createVotoModelSimStub()));
    }

    @Test
    void listEntityToListModel_deveMapearListaEntityParaListaModel() {
        assertEquals(VotoStub.createListVotoModelStub(),
                VotoMapper.listEntityToListModel(VotoStub.createListVotoEntityStub()));
    }

    @Test
    void listModelToListResponse_deveMapearListaModelParaListaResponse() {
        assertEquals(VotoStub.createListVotoResponseStub(),
                VotoMapper.listModelToListResponse(VotoStub.createListVotoModelStub()));
    }

    @Test
    void listModelToListEntity_deveMapearListaModelParaListaEntity() {
        assertEquals(VotoStub.createListVotoEntityStub(),
                VotoMapper.listModelToListEntity(VotoStub.createListVotoModelStub()));
    }

    @Test
    void modelResultadoToResponse_deveMapearModelResultadoParaReponse() {
        assertEquals(VotoStub.createVotoResultadoResponseStub(),
                VotoMapper.modelResultadoToResponse(VotoStub.createVotoResultadoModelStub()));
    }

}