package com.talent.dto;

import com.talent.enums.CompetenciaEnum;
import com.talent.enums.EscalaLikertEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class PerguntaDTO {

    private UUID id;
    private String texto;
    private CompetenciaEnum competencia;
    private EscalaLikertEnum escala;
    private UUID idModelo;

}
