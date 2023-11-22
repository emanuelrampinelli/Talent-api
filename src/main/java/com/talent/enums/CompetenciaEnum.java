package com.talent.enums;

public enum CompetenciaEnum {

    CONHECIMENTO("CONHECIMENTO"),
    HABILIDADES("HABILIDADES"),
    ATITUDES("ATITUDES"),
    RESULTADOS("RESULTADOS");

    private final String competencia;

    CompetenciaEnum(String competencia) {
        this.competencia = competencia;
    }

    public String getCompetencia() {
        return competencia;
    }
}
