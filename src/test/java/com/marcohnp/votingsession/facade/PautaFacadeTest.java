package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.service.PautaService;
import com.marcohnp.votingsession.stub.PautaStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaFacadeTest {

    @InjectMocks
    private PautaFacade facade;

    @Mock
    private PautaService service;

    @Test
    void criarPauta_deveEnviarModelParaServiceERetornarResponse_QuandoRequestForValida() {
        when(service.criarPauta(any())).thenReturn(PautaStub.createPautaModelStub());
        assertEquals(PautaStub.createPautaResponseStub(),
                facade.criarPauta(PautaStub.createPautaRequestStub()));
        verify(service, times(1)).criarPauta(any());
    }

    @Test
    void listarPautas_deveRetornarListadePautas() {
        when(service.listarPautas()).thenReturn(Collections.singletonList(PautaStub.createPautaModelStub()));
        assertEquals(Collections.singletonList(PautaStub.createPautaResponseStub()), facade.listarPautas());
        verify(service, times(1)).listarPautas();
    }

    @Test
    void recuperarPautaPorId_deveEnviarIdParaServiceERetornarResponse_QuandoRequestForValida() {
        when(service.recuperarPautaPorId("61abd13362ecab4fc1246cae")).thenReturn(PautaStub.createPautaModelStub());
        assertEquals(PautaStub.createPautaResponseStub(), facade.recuperarPautaPorId("61abd13362ecab4fc1246cae"));
        verify(service, times(1)).recuperarPautaPorId("61abd13362ecab4fc1246cae");
    }
}