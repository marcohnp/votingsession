package com.marcohnp.votingsession.exception.handler;

import com.marcohnp.votingsession.exception.error.StandardError;
import com.marcohnp.votingsession.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(PautaNotFoundException.class)
    public ResponseEntity<StandardError> pautaNotFound(PautaNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                "Request não completada.", e.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SessaoNotFoundException.class)
    public ResponseEntity<StandardError> sessaoNotFound(SessaoNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                "Request não completada.", e.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<StandardError> cpfInvalido(CpfInvalidoException e, HttpServletRequest request) {
        return new ResponseEntity<>(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Request não completada.", e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<StandardError> integrationError(IntegrationException e, HttpServletRequest request) {
        return new ResponseEntity<>(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Request não completada.", e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CpfNaoHabilitadoParaVotarException.class)
    public ResponseEntity<StandardError> cpfNaoHabilitado(CpfNaoHabilitadoParaVotarException e, HttpServletRequest request) {
        return new ResponseEntity<>(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Request não completada.", e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SessaoEncerradaException.class)
    public ResponseEntity<StandardError> sessaoEncerrada(SessaoEncerradaException e, HttpServletRequest request) {
        return new ResponseEntity<>(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Request não completada.", e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

}
