package com.talent.enums;

public enum TipoQuestionarioEnum {

    AUTO_AVALIACAO("AUTO_AVALIACAO"),
    INTERFACE("INTERFACE"),
    SUPERVISOR("SUPERVISOR"),
    LIDERADO("LIDERADO");

    private final String tipo;

    TipoQuestionarioEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
