package com.talent.enums;

import java.util.List;
import java.util.Arrays;

public enum EscalaLikertEnum {

    IMPORTANCIA(Arrays.asList("Não é importante","As vezes importante","Moderado","Importante","Muito Importante")),
    FREQUENCIA(Arrays.asList("Nunca","Raramente","Ocasionalmente","Frequentemente","Muito Frequente")),
    CONCORDANCIA(Arrays.asList("Discordo Totalmente","Discordo","Não estou decidido","Concordo","Concordo Totalmente")),
    PROBABILIDADE(Arrays.asList("Quase sempre falso","Geralmente Falso","As vezes verdadeiro","Geralmente Verdade","Quase sempre verdade"));

    private final List<String> values;

    EscalaLikertEnum(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
