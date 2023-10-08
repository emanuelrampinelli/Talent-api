package com.talent.dto.usuario.response;



import com.talent.dto.usuario.UsuarioDTO;
import com.talent.dto.usuario.UsuarioTokenDTO;
import com.talent.enums.MensagemResponseEnum;
import lombok.Data;

@Data
public class LoginUsuarioResponseDTO {

    private String message;
    private UsuarioTokenDTO result;

    public LoginUsuarioResponseDTO(MensagemResponseEnum message,
                                   String token, UsuarioDTO usuarioDTO){
        this.message = message.getValue();
        this.result = new UsuarioTokenDTO(token,usuarioDTO);

    }

}
