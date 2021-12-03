package com.marcohnp.votingsession.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoResponse {

    private String id;
    private Integer duracao;
    private LocalDateTime dataInicioSessao;
    private LocalDateTime dataEncerramentoSessao;
    private List<String> cpfsVotantes;
    private List<VotoResponse> votos;
    private String idPauta;
    private Boolean sessaoAberta;
}
