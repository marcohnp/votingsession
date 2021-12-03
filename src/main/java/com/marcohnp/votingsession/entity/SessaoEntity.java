package com.marcohnp.votingsession.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sessoes")
public class SessaoEntity {
    @Id
    private String id;
    private Integer duracao;
    private LocalDateTime dataInicioSessao;
    private LocalDateTime dataEncerramentoSessao;
    private List<String> cpfsVotantes;
    private List<VotoEntity> votos;
    private String idPauta;
    private Boolean sessaoAberta;
}
