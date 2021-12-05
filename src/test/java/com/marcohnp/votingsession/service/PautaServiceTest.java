package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.PautaNotFoundException;
import com.marcohnp.votingsession.model.PautaModel;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.repository.PautaRepository;
import com.marcohnp.votingsession.stub.PautaStub;
import com.marcohnp.votingsession.stub.SessaoStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Test
    void criarPauta_deveSalvarPautaNoRepositoryERetornarModel_quandoRequestForValida() {
        var model = PautaStub.createPautaModelStub();
        var entity = PautaStub.createPautaEntityStub();
        when(repository.save(any())).thenReturn(entity);
        assertEquals(model, service.criarPauta(model));
        verify(repository, times(1)).save(entity);
    }

    @Test
    void salvarSessaoEmPauta_deveLancarException_quandoPautaNaoForEncontrada() {
        when(repository.findById("61abd13362ecab4fc1246cae")).thenReturn(Optional.empty());
        var expected = new PautaNotFoundException("Pauta não encontada");
        var exception = assertThrows(PautaNotFoundException.class,
                () -> service.salvarSessaoEmPauta(SessaoStub.createSessaoModelStub()), "Pauta não encontrada");
        assertEquals(expected.getMessage(), exception.getMessage());
        verify(repository, times(1)).findById("61abd13362ecab4fc1246cae");
    }

    @Test
    void listarPautas_deveListarPautas() {
        var model = PautaStub.createPautaModelStub();
        when(repository.findAll()).thenReturn(Collections.singletonList(PautaStub.createPautaEntityStub()));
        assertEquals(Collections.singletonList(model), service.listarPautas());
        verify(repository, times(1)).findAll();
    }

    @Test
    void recuperarPautaPorId_deveRecuperarPautaConformeId() {
        when(repository.findById("61abd13362ecab4fc1246cae")).thenReturn(Optional.of(PautaStub.createPautaEntityStub()));
        assertEquals(PautaStub.createPautaModelStub(), service.recuperarPautaPorId("61abd13362ecab4fc1246cae"));
        verify(repository, times(1)).findById("61abd13362ecab4fc1246cae");
    }

}