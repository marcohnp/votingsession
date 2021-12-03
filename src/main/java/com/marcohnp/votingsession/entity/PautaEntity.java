package com.marcohnp.votingsession.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pautas")
public class PautaEntity {

    @Id
    private String id;
    private String pauta;
    private SessaoEntity sessao;
}
