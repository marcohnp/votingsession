package com.marcohnp.votingsession.mapper;

import com.marcohnp.votingsession.entity.VotoEntity;
import com.marcohnp.votingsession.enums.VotoEnum;
import com.marcohnp.votingsession.model.VotoModel;
import com.marcohnp.votingsession.model.request.VotoRequest;
import com.marcohnp.votingsession.model.response.VotoResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoMapper {


    public static VotoModel requestToModel(VotoRequest request) {
        if (ObjectUtils.isEmpty(request)) return null;
        return VotoModel.builder()
                .cpf(request.getCpf())
                .voto(VotoEnum.fromValue(request.getVoto()))
                .build();
    }

    public static VotoEntity modelToEntity(VotoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return VotoEntity.builder()
                .cpf(model.getCpf())
                .voto(model.getVoto())
                .dataVoto(model.getDataVoto())
                .idSessao(model.getIdSessao())
                .build();
    }

    public static VotoModel entityToModel(VotoEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;
        return VotoModel.builder()
                .cpf(entity.getCpf())
                .voto(entity.getVoto())
                .dataVoto(entity.getDataVoto())
                .idSessao(entity.getIdSessao())
                .build();
    }

    public static VotoResponse modelToResponse(VotoModel model) {
        if (ObjectUtils.isEmpty(model)) return null;
        return VotoResponse.builder()
                .cpf(model.getCpf())
                .voto(model.getVoto())
                .dataVoto(model.getDataVoto())
                .idSessao(model.getIdSessao())
                .build();
    }

    public static List<VotoModel> listEntityToListModel(List<VotoEntity> entityList) {
        if (ObjectUtils.isEmpty(entityList)) return null;
        return entityList.stream().map(VotoMapper::entityToModel).collect(Collectors.toList());
    }

    public static List<VotoResponse> listModelToListResponse(List<VotoModel> modelList) {
        if (ObjectUtils.isEmpty(modelList)) return null;
        return modelList.stream().map(VotoMapper::modelToResponse).collect(Collectors.toList());
    }

    public static List<VotoEntity> listModelToListEntity(List<VotoModel> modelList) {
        if (ObjectUtils.isEmpty(modelList)) return null;
        return modelList.stream().map(VotoMapper::modelToEntity).collect(Collectors.toList());
    }
}
