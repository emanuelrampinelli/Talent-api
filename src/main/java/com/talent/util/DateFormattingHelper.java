package com.talent.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe de utilitário para formatação de datas e horas.
 */
@Slf4j
public class DateFormattingHelper {

    /**
     * Formata um objeto DateTime em uma string no formato "yyyy/MM/dd HH:mm:ss".
     *
     * @param data O objeto DateTime a ser formatado.
     * @return A data formatada como uma string SQL.
     */
    public static String formatDateTimeToStringSQL(DateTime data) {
        if (data == null) return null;
        int ano = data.getYearOfEra();
        int mes = data.getMonthOfYear();
        int dia = data.getDayOfMonth();
        int hora = data.getHourOfDay();
        int min = data.getMinuteOfHour();
        int seg = data.getSecondOfMinute();
        return ano + "/" + mes + "/" + dia + " " + hora + ":" + min + ":" + seg;
    }

    /**
     * Converte uma string no formato "dd/MM/yyyy" em um objeto Date.
     *
     * @param data A data no formato "dd/MM/yyyy".
     * @return Um objeto Date representando a data ou null se a conversão falhar.
     */
    public static Date formatDateBarra(String data) {
        if (data == null) return null;
        String dateFormat = "dd/MM/yyyy"; // Formato da string de data
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            java.util.Date dateUtil = sdf.parse(data); // Converte a string em java.util.Date
            return new Date(dateUtil.getTime());
        } catch (ParseException e) {
            log.error("Falha ao conveter data o formato \"dd/MM/yyyy\" : "+ e);
            return null;
        }
    }

    /**
     * Converte uma string no formato "dd-MM-yyyy" em um objeto Date.
     *
     * @param data A data no formato "dd-MM-yyyy".
     * @return Um objeto Date representando a data ou null se a conversão falhar.
     */
    public static Date formatDateTraco(String data) {

        if (data == null) return null;
        String dateFormat = "dd-MM-yyyy"; // Formato da string de data
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            java.util.Date dateUtil = sdf.parse(data); // Converte a string em java.util.Date
            return new Date(dateUtil.getTime());
        } catch (ParseException e) {
            log.error("Falha ao conveter data o formato \"dd-MM-yyyy\" : "+ e);
            return null;
        }
    }

    /**
     * Converte uma string de data no formato "yyyy-MM-dd" para um objeto DateTime.
     *
     * @param date A string de data no formato "yyyy-MM-dd".
     * @return Um objeto DateTime representando a data fornecida.
     * @throws NumberFormatException Se a string de data não estiver no formato esperado.
     */
    public static DateTime formatStringToDateTime(String date) throws NumberFormatException {

        if (date == null) return null;

        // Extrai ano, mês e dia da string de data
        int ano = Integer.parseInt(date.substring(0, 4));
        int mes = Integer.parseInt(date.substring(5, 7));
        int dia = Integer.parseInt(date.substring(8, 10));

        // Retorna um objeto DateTime representando a data
        return new DateTime(ano, mes, dia, 0, 0, 0);
    }

    /**
     * Converte uma string no formato "dd-MM-yyyy" para um objeto LocalDate.
     *
     * @param dataString A string que representa a data no formato "dd-MM-yyyy".
     * @return Um objeto LocalDate representando a data.
     */
    public static LocalDate formatStringToLocalDateSimplesTraco(String dataString) {

        // Cria um formatador de data com o padrão desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Converte a string para um objeto LocalDate usando o formatador
        return LocalDate.parse(dataString, formatter);
    }

    /**
     * Converte uma string no formato "dd/MM/yyyy" para um objeto LocalDate.
     *
     * @param dataString A string que representa a data no formato "dd/MM/yyyy".
     * @return Um objeto LocalDate representando a data.
     */
    public static LocalDate formatStringToLocalDateSimplesBarra(String dataString) {

        // Cria um formatador de data com o padrão desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converte a string para um objeto LocalDate usando o formatador
        return LocalDate.parse(dataString, formatter);
    }

    /**
     * Converte uma string no formato "yyyy-MM-dd HH:mm:ss.SSSSSS" para um objeto LocalDate.
     *
     * @param dataHoraString A string que representa a data e hora no formato "yyyy-MM-dd HH:mm:ss.SSSSSS".
     * @return Um objeto LocalDate representando a data.
     */
    public static LocalDate formatStringToLocalDateCompleto(String dataHoraString) {

        // Cria um formatador de data com o padrão desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Converte a string para um objeto LocalDate usando o formatador
        return LocalDate.parse(dataHoraString, formatter);
    }

    /**
     * Valida e formata uma string de data em um objeto Date.
     *
     * Este método aceita datas nos formatos "dd-MM-yyyy" e "dd/MM/yyyy".
     *
     * @param data A string que representa a data.
     * @return Um objeto Date se a string for um dos formatos aceitos, caso contrário, retorna null.
     */
    public static Date validarFormatarDate(String data) {

        // Padrão de expressão regular para verificar o formato de data dd-MM-yyyy
        String padraoDDMMYYYY = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";

        // Padrão de expressão regular para verificar o formato de data dd/MM/yyyy
        String padraoDDMMYYYYBarra = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        // Compilação dos padrões de expressão regular
        Pattern regexDDMMYYYY = Pattern.compile(padraoDDMMYYYY);
        Pattern regexDDMMYYYYBarra = Pattern.compile(padraoDDMMYYYYBarra);

        // Criação dos matchers
        Matcher matcherDDMMYYYY = regexDDMMYYYY.matcher(data);
        Matcher matcherDDMMYYYYBarra = regexDDMMYYYYBarra.matcher(data);

        // Verificação dos padrões e formatação da data
        if (matcherDDMMYYYY.matches()) {
            return DateFormattingHelper.formatDateTraco(data);
        } else if (matcherDDMMYYYYBarra.matches()) {
            return DateFormattingHelper.formatDateBarra(data);
        } else {
            return null;
        }
    }
}
