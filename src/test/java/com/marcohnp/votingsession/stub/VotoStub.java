package com.marcohnp.votingsession.stub;

import com.marcohnp.votingsession.entity.VotoEntity;
import com.marcohnp.votingsession.enums.VotoEnum;
import com.marcohnp.votingsession.model.VotoModel;
import com.marcohnp.votingsession.model.VotoResultadoModel;
import com.marcohnp.votingsession.model.request.VotoRequest;
import com.marcohnp.votingsession.model.response.VotoResponse;
import com.marcohnp.votingsession.model.response.VotoResultadoResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VotoStub {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static VotoRequest createVotoRequestSimStub() {
        return VotoRequest.builder()
                .cpf("22714037070")
                .voto("SIM")
                .build();
    }

    public static VotoRequest createVotoRequestNaoStub() {
        return VotoRequest.builder()
                .cpf("74568706084")
                .voto("NAO")
                .build();
    }

    public static VotoModel createVotoModelSimStub() {
        return VotoModel.builder()
                .cpf("22714037070")
                .voto(VotoEnum.SIM)
                .dataVoto(LocalDateTime.parse("01/05/2021 18:32:00", formatter))
                .idSessao("61abd13362ecab4fc1246cae")
                .build();
    }

    public static VotoModel createVotoModelNaoStub() {
        return VotoModel.builder()
                .cpf("74568706084")
                .voto(VotoEnum.NAO)
                .dataVoto(LocalDateTime.parse("01/05/2021 18:33:00", formatter))
                .idSessao("61abd13362ecab4fc1246cae")
                .build();
    }

    public static List<VotoModel> createListVotoModelStub() {
        return List.of(createVotoModelSimStub(), createVotoModelNaoStub());
    }

    public static VotoResponse createVotoResponseSimStub() {
        return VotoResponse.builder()
                .cpf("22714037070")
                .voto(VotoEnum.SIM)
                .dataVoto(LocalDateTime.parse("01/05/2021 18:32:00", formatter))
                .idSessao("61abd13362ecab4fc1246cae")
                .build();
    }

    public static VotoResponse createVotoResponseNaoStub() {
        return VotoResponse.builder()
                .cpf("74568706084")
                .voto(VotoEnum.NAO)
                .dataVoto(LocalDateTime.parse("01/05/2021 18:33:00", formatter))
                .idSessao("61abd13362ecab4fc1246cae")
                .build();
    }

    public static List<VotoResponse> createListVotoResponseStub() {
        return List.of(createVotoResponseSimStub(), createVotoResponseNaoStub());
    }

    public static VotoEntity createVotoEntitySimStub() {
        return VotoEntity.builder()
                .cpf("22714037070")
                .voto(VotoEnum.SIM)
                .dataVoto(LocalDateTime.parse("01/05/2021 18:32:00", formatter))
                .idSessao("61abd13362ecab4fc1246cae")
                .build();
    }

    public static VotoEntity createVotoEntityNaoStub() {
        return VotoEntity.builder()
                .cpf("74568706084")
                .voto(VotoEnum.NAO)
                .dataVoto(LocalDateTime.parse("01/05/2021 18:33:00", formatter))
                .idSessao("61abd13362ecab4fc1246cae")
                .build();
    }

    public static List<VotoEntity> createListVotoEntityStub() {
        return List.of(createVotoEntitySimStub(), createVotoEntityNaoStub());
    }

    public static VotoResultadoModel createVotoResultadoModelStub() {
        return VotoResultadoModel.builder()
                .sim(1)
                .nao(1)
                .build();
    }

    public static VotoResultadoResponse createVotoResultadoResponseStub() {
        return VotoResultadoResponse.builder()
                .sim(1)
                .nao(1)
                .build();
    }
}
