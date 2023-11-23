package com.talent.enums;

public enum AvaliacaoStatusEnum {

    CRIADO("CRIADO"),
    EM_ANDAMENTO("EM_ANDAMENTO"),
    ENCERRADO("ENCERRADO"),
    PAUSADA("PAUSADA");

    private final String tipo;

    AvaliacaoStatusEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
