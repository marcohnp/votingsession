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
public class PautaRequest {
    @NotEmpty(message = "Pauta deve ser informada.")
    private String pauta;
}
