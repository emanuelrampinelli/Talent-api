package com.talent.enums;

public enum AlternativaEnum {

    ALTERNATIVA_01(1),
    ALTERNATIVA_02(2),
    ALTERNATIVA_03(3),
    ALTERNATIVA_04(4),
    ALTERNATIVA_05(5);

    private final int ordem;

    AlternativaEnum(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }
}
