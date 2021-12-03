package com.marcohnp.votingsession.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResultadoModel {

    private Integer sim;
    private Integer nao;
}
