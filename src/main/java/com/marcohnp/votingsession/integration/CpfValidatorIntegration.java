package com.marcohnp.votingsession.integration;

import com.marcohnp.votingsession.exception.exceptions.CpfInvalidoException;
import com.marcohnp.votingsession.exception.exceptions.IntegrationException;
import com.marcohnp.votingsession.integration.response.CpfValidatorResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class CpfValidatorIntegration {

    private final RestTemplate restTemplate;
    private final String url;

    @Autowired
    public CpfValidatorIntegration(RestTemplate restTemplate,  @Value("${cpf.validator.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

//    @PostConstruct
//    public void init() {
//        validarCpf("99999999900");
//    }


    public CpfValidatorResponse validarCpf(String cpf) {
        try {
            ResponseEntity<CpfValidatorResponse> response = restTemplate.exchange(url + cpf, HttpMethod.GET, null, CpfValidatorResponse.class);
            var teste = response.getBody();
            return teste;
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode()== HttpStatus.NOT_FOUND)
                throw new CpfInvalidoException("CPF Inválido!");

            throw new IntegrationException("Aconteceu algum erro ao acessar serviço de validação de CPF");
        }
    }
}
