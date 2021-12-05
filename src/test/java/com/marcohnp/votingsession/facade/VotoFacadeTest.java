package com.marcohnp.votingsession.facade;

import com.marcohnp.votingsession.service.VotoService;
import com.marcohnp.votingsession.stub.VotoStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotoFacadeTest {

    @InjectMocks
    private VotoFacade facade;

    @Mock
    private VotoService service;

    @Test
    void enviarVotoParaSessao_deveEnviarModelEIdSessaoParaServiceERetornarResponse_QuandoRequestForValida() {
        when(service.enviarVotoParaSessao(any(), any())).thenReturn(VotoStub.createVotoModelSimStub());
        assertEquals(VotoStub.createVotoResponseSimStub(),
                facade.enviarVotoParaSessao(VotoStub.createVotoRequestSimStub(), "61abd13362ecab4fc1246cae"));
        verify(service, times(1)).enviarVotoParaSessao(any(), any());
    }

}