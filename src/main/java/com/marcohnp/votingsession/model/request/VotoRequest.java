package com.marcohnp.votingsession.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoRequest {

    @NotEmpty(message = "CPF deve ser informado.")
    private String cpf;
    @NotEmpty(message = "Voto deve ser SIM ou NAO.")
    private String voto;
}
