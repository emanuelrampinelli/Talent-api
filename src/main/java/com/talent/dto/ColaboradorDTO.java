package com.talent.dto;
import com.talent.dto.usuario.UsuarioDTO;
import com.talent.enums.ColaboradorStatusEnum;
import lombok.Data;
import java.util.UUID;

@Data
public class ColaboradorDTO {

    private UUID id;
    private String nome;
    private String matricula;
    private ColaboradorStatusEnum situacao;
    private String dataNascimento;
    private String dataAdmissao;
    private String dataDesligamento;
    private UUID idInstituicao;
    private UUID idCargo;
    private UsuarioDTO usuario = new UsuarioDTO();
}
