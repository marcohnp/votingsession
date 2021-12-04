package com.marcohnp.votingsession.mapper;

import com.marcohnp.votingsession.entity.SessaoEntity;
import com.marcohnp.votingsession.kafka.model.SessaoKafkaModel;
import com.marcohnp.votingsession.model.SessaoModel;
import com.marcohnp.votingsession.model.SessaoResultadoModel;
import com.marcohnp.votingsession.model.request.SessaoRequest;
import com.marcohnp.votingsession.model.response.SessaoResponse;
import com.marcohnp.votingsession.model.response.SessaoResultadoResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoMapper {

    public static SessaoModel requestToModel(SessaoRequest request) {
        if (ObjectUtils.isEmpty(request)) return null;
        return SessaoModel.builder()
                .duracao(request.getDuracao())
                .dataInicioSessao(LocalDateTime.now())
                .build();
    }

    public static SessaoEntity modelToEntity(SessaoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return SessaoEntity.builder()
                .id(model.getId())
                .idPauta(model.getIdPauta())
                .duracao(model.getDuracao())
                .dataInicioSessao(model.getDataInicioSessao())
                .dataEncerramentoSessao(model.getDataEncerramentoSessao())
                .votos(VotoMapper.listModelToListEntity(model.getVotos()))
                .cpfsVotantes(model.getCpfsVotantes())
                .sessaoAberta(model.getSessaoAberta())
                .build();
    }

    public static SessaoModel entityToModel(SessaoEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;
        return SessaoModel.builder()
                .id(entity.getId())
                .duracao(entity.getDuracao())
                .dataInicioSessao(entity.getDataInicioSessao())
                .dataEncerramentoSessao(entity.getDataEncerramentoSessao())
                .cpfsVotantes(entity.getCpfsVotantes())
                .idPauta(entity.getIdPauta())
                .votos(VotoMapper.listEntityToListModel(entity.getVotos()))
                .sessaoAberta(entity.getSessaoAberta())
                .build();
    }

    public static SessaoResponse modelToResponse(SessaoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return SessaoResponse.builder()
                .id(model.getId())
                .duracao(model.getDuracao())
                .dataInicioSessao(model.getDataInicioSessao())
                .dataEncerramentoSessao(model.getDataEncerramentoSessao())
                .cpfsVotantes(model.getCpfsVotantes())
                .idPauta(model.getIdPauta())
                .votos(VotoMapper.listModelToListResponse(model.getVotos()))
                .sessaoAberta(model.getSessaoAberta())
                .build();
    }

    public static SessaoResultadoResponse resultadoModelToResponse(SessaoResultadoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return SessaoResultadoResponse.builder()
                .id(model.getId())
                .dataInicioSessao(model.getDataInicioSessao())
                .dataEncerramentoSessao(model.getDataEncerramentoSessao())
                .cpfsVotantes(model.getCpfsVotantes())
                .resultadoVotacao(VotoMapper.modelResultadoToResponse(model.getResultadoVotacao()))
                .idPauta(model.getIdPauta())
                .build();
    }

    public static SessaoResultadoModel modelToResultadoModel(SessaoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return SessaoResultadoModel.builder()
                .id(model.getId())
                .dataInicioSessao(model.getDataInicioSessao())
                .dataEncerramentoSessao(model.getDataEncerramentoSessao())
                .cpfsVotantes(model.getCpfsVotantes())
                .idPauta(model.getIdPauta())
                .build();
    }

    public static SessaoKafkaModel resultadoModelToKafka(SessaoResultadoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return SessaoKafkaModel.builder()
                .id(model.getId())
                .dataInicioSessao(model.getDataInicioSessao())
                .dataEncerramentoSessao(model.getDataEncerramentoSessao())
                .cpfsVotantes(model.getCpfsVotantes())
                .resultadoVotacao(model.getResultadoVotacao())
                .idPauta(model.getIdPauta())
                .build();
    }

}
