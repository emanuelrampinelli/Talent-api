package com.talent.enums;

public enum ColaboradorStatusEnum {

    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String status;

    ColaboradorStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
