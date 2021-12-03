package com.marcohnp.votingsession.enums;

import java.util.EnumSet;

public enum VotoEnum {

    SIM(1, "SIM"),
    NAO(2, "NAO");

    private final Integer cod;
    private final String descricao;

    VotoEnum(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static VotoEnum fromValue(String value) {
        return EnumSet.allOf(VotoEnum.class)
                .stream()
                .filter(formaPagamentoEnum -> formaPagamentoEnum.getDescricao().equals(value))
                .findFirst()
                .orElse(null);
    }
}
