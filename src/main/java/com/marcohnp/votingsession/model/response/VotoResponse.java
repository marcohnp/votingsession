package com.marcohnp.votingsession.model.response;

import com.marcohnp.votingsession.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponse {

    private String cpf;
    private VotoEnum voto;
    private LocalDateTime dataVoto;
    private String idSessao;
}
