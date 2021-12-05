package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.SessaoNotFoundException;
import com.marcohnp.votingsession.provider.DateTimeProvider;
import com.marcohnp.votingsession.repository.SessaoRepository;
import com.marcohnp.votingsession.stub.SessaoStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService sessaoService;

    @Mock
    private PautaService pautaService;

    @Mock
    private SessaoRepository repository;

    @Mock
    private DateTimeProvider provider;

    @Test
    void criarSessaoParaPauta_deveCriarSessaoERetornarSessaoModel_quandoRequestForValida() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 5, 1, 18, 30, 0);
        var entity = SessaoStub.createSessaoEntityStub();
        entity.setSessaoAberta(Boolean.TRUE);
        var model = SessaoStub.createSessaoModelStub();
        model.setSessaoAberta(Boolean.TRUE);
        when(repository.save(any())).thenReturn(entity);
        when(provider.now()).thenReturn(dateTime);
        assertEquals(model, sessaoService.criarSessaoParaPauta(model, "61abd13362ecab4fc1246cae"));
        verify(repository, times(1)).save(any());
    }

    @Test
    void recuperarSessaoPorId_deveRetornarSessaoConformeIdInformado_quandoRequestForValida() {
        when(repository.findById("61abd13362ecab4fc1246cae")).thenReturn(Optional.of(SessaoStub.createSessaoEntityStub()));
        assertEquals(SessaoStub.createSessaoModelStub(), sessaoService.recuperarSessaoPorId("61abd13362ecab4fc1246cae"));
        verify(repository, times(1)).findById("61abd13362ecab4fc1246cae");
    }

    @Test
    void recuperarSessaoPorId_deveLancarException_quandoSessaoNaoForEncontrada() {
        when(repository.findById("61abd13362ecab4fc1246cae")).thenReturn(Optional.empty());
        var expected = new SessaoNotFoundException("Sessao não encontada");
        var exception = assertThrows(SessaoNotFoundException.class,
                () -> sessaoService.recuperarResultadoSessao("61abd13362ecab4fc1246cae"), "Sessao não encontada");
        assertEquals(expected.getMessage(), exception.getMessage());
        verify(repository, times(1)).findById("61abd13362ecab4fc1246cae");
    }

    @Test
    void recuperarResultadoSessao_deveRecuperarResultadoDaSessao_quandoRequestForValida() {
        when(repository.findById("61abd13362ecab4fc1246cae")).thenReturn(Optional.of(SessaoStub.createSessaoEntityStub()));
        assertEquals(SessaoStub.createSessaoResultadoModelStub(), sessaoService.recuperarResultadoSessao("61abd13362ecab4fc1246cae"));
        verify(repository, times(1)).findById("61abd13362ecab4fc1246cae");
    }

}