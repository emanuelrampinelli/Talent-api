package com.talent.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe de utilitário para validação e formatação de datas.
 */
public class DateValidationHelper {

    /**
     * Verifica se os parâmetros de mês e ano são válidos.
     *
     * @param mes O mês a ser validado.
     * @param ano O ano a ser validado.
     * @return true se os parâmetros forem válidos, false se forem inválidos.
     */
    public static boolean isMesAnoValido(Integer mes, Integer ano) {
        return mes != null && mes >= 1 && mes <= 12 && ano != null && ano >= 1900;
    }

    /**
     * Verifica se a string fornecida está em um formato de data válido ("dd-MM-yyyy" ou "dd/MM/yyyy").
     *
     * @param data A string que representa a data.
     * @return true se a string estiver em um formato de data válido, caso contrário, false.
     */
    public static boolean isDataValida(String data) {
        // Verifica se a data está no formato "dd-MM-yyyy" ou "dd/MM/yyyy"
        return isDataValidadeTraco(data) || isDataValidadeBarra(data);
    }


    /**
     * Verifica se a string fornecida está em um formato de data válido ("dd/MM/yyyy").
     *
     * @param data A string que representa a data.
     * @return true se a string estiver em um formato de data válido, caso contrário, false.
     */
    public static boolean isDataValidadeBarra(String data) {

        // Padrão de expressão regular para verificar o formato de data dd/MM/yyyy
        String padraoDDMMYYYYBarra = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        // Compilação do padrão de expressão regular
        Pattern regexDDMMYYYYBarra = Pattern.compile(padraoDDMMYYYYBarra);

        // Criação do matcher
        Matcher matcherDDMMYYYYBarra = regexDDMMYYYYBarra.matcher(data);

        // Verificação se a string está em um formato de data válido
        return matcherDDMMYYYYBarra.matches();
    }

    /**
     * Verifica se a string fornecida está em um formato de data válido ("dd-MM-yyyy").
     *
     * @param data A string que representa a data.
     * @return true se a string estiver em um formato de data válido, caso contrário, false.
     */
    public static boolean isDataValidadeTraco(String data) {

        // Padrão de expressão regular para verificar o formato de data dd-MM-yyyy
        String padraoDDMMYYYY = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";

        // Compilação do padrão de expressão regular
        Pattern regexDDMMYYYY = Pattern.compile(padraoDDMMYYYY);

        // Criação do matcher
        Matcher matcherDDMMYYYY = regexDDMMYYYY.matcher(data);

        // Verificação se a string está em um formato de data válido
        return matcherDDMMYYYY.matches();
    }

}
