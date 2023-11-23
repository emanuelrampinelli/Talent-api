package com.talent.enums;

public enum QuestionarioStatusEnum {

    NAO_INICIADO(1),
    INCOMPLETO(2),
    COMPLETO(3),
    SUBMETIDO(4);

    private final int status;

    QuestionarioStatusEnum(int ordem) {
        this.status = ordem;
    }

    public int getStatus() {
        return status;
    }
}
