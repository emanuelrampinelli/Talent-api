package com.talent.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.talent.enums.AvaliacaoStatusEnum;
import com.talent.model.Equipe;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvaliacaoDTO {

    private String titulo;
    private String dataInicio;
    private String dataTermino;
    private String descricao;
    private AvaliacaoStatusEnum status;
    private UUID idModeloAutoAvaliacao;
    private UUID idModeloInterface;
    private UUID idModeloSupervisor;
    private UUID idModeloLiderado;
    private List<UUID>  idEquipes;
    private Equipe equipes;
    private UUID idInstituicao;

}
