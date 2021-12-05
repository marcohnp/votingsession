package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.service.SessaoService;
import com.marcohnp.votingsession.stub.SessaoStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoFacadeTest {

    @InjectMocks
    private SessaoFacade facade;

    @Mock
    private SessaoService service;

    @Test
    void criarSessaoParaPauta_deveEnviarModelEIdPautaParaServiceERetornarResponse_QuandoRequestForValida() {
        when(service.criarSessaoParaPauta(any(), any())).thenReturn(SessaoStub.createSessaoModelStub());
        assertEquals(SessaoStub.createSessaoResponseStub(),
                facade.criarSessaoParaPauta(SessaoStub.createSessaoRequestStub(), "61abd13362ecab4fc1246cae"));
        verify(service, times(1)).criarSessaoParaPauta(any(), any());
    }

    @Test
    void recuperarSessaoPorId_deveEnviarIdParaServiceERetornarResponse_QuandoRequestForValida() {
        when(service.recuperarSessaoPorId("61abd13362ecab4fc1246cae")).thenReturn(SessaoStub.createSessaoModelStub());
        assertEquals(SessaoStub.createSessaoResponseStub(), facade.recuperarSessaoPorId("61abd13362ecab4fc1246cae"));
        verify(service, times(1)).recuperarSessaoPorId("61abd13362ecab4fc1246cae");
    }

    @Test
    void finalizarSessao_deveFinalizarSessao_quandoIdForInformado() {
        when(service.finalizarSessaoManualmente("61abd13362ecab4fc1246cae")).thenReturn("Sessão 61abd13362ecab4fc1246cae encerrada.");
        assertEquals("Sessão 61abd13362ecab4fc1246cae encerrada.", facade.finalizarSessao("61abd13362ecab4fc1246cae"));
        verify(service, times(1)).finalizarSessaoManualmente("61abd13362ecab4fc1246cae");
    }

    @Test
    void recuperarResultadoSessao_deveEnviarIdParaServiceERetornarResultadoResponse_quandoRequestForValida() {
        when(service.recuperarResultadoSessao("61abd13362ecab4fc1246cae")).thenReturn(SessaoStub.createSessaoResultadoModelStub());
        assertEquals(SessaoStub.createSessaoResultadoResponseStub(),
                facade.recuperarResultadoSessao("61abd13362ecab4fc1246cae"));
        verify(service, times(1)).recuperarResultadoSessao("61abd13362ecab4fc1246cae");
    }

}