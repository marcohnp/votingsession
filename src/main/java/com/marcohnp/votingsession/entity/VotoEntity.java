package com.marcohnp.votingsession.entity;

import com.marcohnp.votingsession.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "votos")
public class VotoEntity {

    @Id
    private String Id;
    private String cpf;
    private VotoEnum voto;
    private LocalDateTime dataVoto;
    private String idSessao;
}
