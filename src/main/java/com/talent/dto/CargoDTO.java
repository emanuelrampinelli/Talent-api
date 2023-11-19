package com.talent.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CargoDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private UUID idEmpresa;
}
