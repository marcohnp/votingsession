package com.marcohnp.votingsession.mapper;

import com.marcohnp.votingsession.entity.PautaEntity;
import com.marcohnp.votingsession.model.PautaModel;
import com.marcohnp.votingsession.model.request.PautaRequest;
import com.marcohnp.votingsession.model.response.PautaResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaMapper {

    public static PautaModel requestToModel(PautaRequest request) {
        if (ObjectUtils.isEmpty(request)) return null;
        return PautaModel.builder()
                .pauta(request.getPauta())
                .build();
    }

    public static PautaEntity modelToEntity(PautaModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return PautaEntity.builder()
                .id(model.getId())
                .pauta(model.getPauta())
                .sessao(SessaoMapper.modelToEntity(model.getSessao()))
                .build();
    }

    public static PautaModel entityToModel(PautaEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;
        return PautaModel.builder()
                .id(entity.getId())
                .pauta(entity.getPauta())
                .sessao(SessaoMapper.entityToModel(entity.getSessao()))
                .build();
    }

    public static PautaResponse modelToResponse(PautaModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return PautaResponse.builder()
                .id(model.getId())
                .pauta(model.getPauta())
                .sessao(SessaoMapper.modelToResponse(model.getSessao()))
                .build();
    }
}
