package com.marcohnp.votingsession.model;

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
public class SessaoResultadoModel {

    private String id;
    private LocalDateTime dataInicioSessao;
    private LocalDateTime dataEncerramentoSessao;
    private List<String> cpfsVotantes;
    private VotoResultadoModel resultadoVotacao;
    private String idPauta;
}
