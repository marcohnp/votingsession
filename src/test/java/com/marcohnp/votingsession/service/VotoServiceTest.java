package com.marcohnp.votingsession.service;

import com.marcohnp.votingsession.exception.exceptions.CpfNaoHabilitadoParaVotarException;
import com.marcohnp.votingsession.integration.CpfValidatorIntegration;
import com.marcohnp.votingsession.integration.response.CpfValidatorResponse;
import com.marcohnp.votingsession.provider.DateTimeProvider;
import com.marcohnp.votingsession.repository.VotoRepository;
import com.marcohnp.votingsession.stub.VotoStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository repository;

    @Mock
    private CpfValidatorIntegration integration;

    @Mock
    private DateTimeProvider provider;

    @Test
    void enviarVotoParaSessao_deveEnviarVotoParaSessaoERetornarVotoModel() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 5, 1, 18, 32, 0);

        var votoModel = VotoStub.createVotoModelSimStub();
        var cpfValidator = CpfValidatorResponse.builder()
                .status("ABLE_TO_VOTE")
                .build();

        when(provider.now()).thenReturn(dateTime);
        when(repository.save(any())).thenReturn(VotoStub.createVotoEntitySimStub());
        when(integration.validarCpf("22714037070")).thenReturn(cpfValidator);

        assertEquals(votoModel, votoService.enviarVotoParaSessao(votoModel, "61abd13362ecab4fc1246cae"));
        verify(repository, times(1)).save(any());
        verify(integration, times(1)).validarCpf("22714037070");
    }

    @Test
    void enviarVotoParaSessao_deveLancarExcepetion_quandoCPFnaoForHalitadoParaVotar() {
        var cpfValidator = CpfValidatorResponse.builder()
                .status("UNABLE_TO_VOTE")
                .build();

        when(integration.validarCpf(any())).thenReturn(cpfValidator);

        var expected = new CpfNaoHabilitadoParaVotarException("CPF informado não está habilitado para votar");
        var exception = assertThrows(CpfNaoHabilitadoParaVotarException.class,
                () -> votoService.enviarVotoParaSessao(VotoStub.createVotoModelSimStub(), "61abd13362ecab4fc1246cae"),
                "CPF informado não está habilitado para votar");

        assertEquals(expected.getMessage(), exception.getMessage());
        verify(integration, times(1)).validarCpf("22714037070");
    }
}