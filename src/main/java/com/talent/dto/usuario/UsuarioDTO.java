package com.talent.dto.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.talent.enums.UsuarioPerfilEnum;
import com.talent.model.Usuario;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class UsuarioDTO {

    private UUID id;

    private UsuarioPerfilEnum perfil;

    @JsonIgnore private String dataCadastro;

    private boolean isBloqueado;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    public UsuarioDTO (Usuario usuario){
        BeanUtils.copyProperties(usuario,this);
    }

    public UsuarioDTO (){}
}
