package com.marcohnp.votingsession.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaModel {
    private String id;
    private String pauta;
    private SessaoModel sessao;
}
