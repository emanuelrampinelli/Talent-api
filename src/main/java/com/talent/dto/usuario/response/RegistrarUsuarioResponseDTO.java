package com.talent.dto.usuario.response;

import com.talent.dto.usuario.UsuarioDTO;
import com.talent.enums.MensagemResponseEnum;
import lombok.Data;

@Data
public class RegistrarUsuarioResponseDTO {

    private String message;
    private UsuarioDTO result;

    public RegistrarUsuarioResponseDTO(MensagemResponseEnum message, UsuarioDTO result){
        this.message = message.getValue();
        this.result = result;
    }

}
