package com.talent.dto;

import com.talent.enums.TipoQuestionarioEnum;
import lombok.Data;
import java.util.UUID;

@Data
public class ModeloQuestionarioDTO {

    private UUID id;
    private String nome;
    private TipoQuestionarioEnum tipo;
    private String descricao;
    private int quantidadePerguntas;
    private UUID idInstituicao;
}
