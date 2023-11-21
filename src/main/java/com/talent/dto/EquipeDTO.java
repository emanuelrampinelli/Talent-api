package com.talent.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EquipeDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private UUID idLider;
    private List<ColaboradorDTO> colaboradores;
}
