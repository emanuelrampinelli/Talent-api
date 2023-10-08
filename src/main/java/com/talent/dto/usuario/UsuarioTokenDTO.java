package com.talent.dto.usuario;

import lombok.Data;

@Data
public class UsuarioTokenDTO {

    private String token;
    private UsuarioDTO usuario;

    public UsuarioTokenDTO (String token, UsuarioDTO usuario){
        this.token = token;
        this.usuario = usuario;
    }
}
