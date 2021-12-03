package com.marcohnp.votingsession.model;

import com.marcohnp.votingsession.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoModel {

    private String cpf;
    private VotoEnum voto;
    private LocalDateTime dataVoto;
    private String idSessao;
}
