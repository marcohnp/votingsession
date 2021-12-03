package com.marcohnp.votingsession.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResultadoResponse {

    private Integer sim;
    private Integer nao;
}
