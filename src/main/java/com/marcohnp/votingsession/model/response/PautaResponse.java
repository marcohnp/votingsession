package com.marcohnp.votingsession.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaResponse {
    private String id;
    private String pauta;
    private SessaoResponse sessao;
}
