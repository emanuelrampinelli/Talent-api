package com.talent.dto.usuario.request;

import com.talent.enums.UsuarioPerfilEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrarUsuarioRequestDTO {

    @NotNull private String nome;

    @NotNull private String email;

    @NotNull private String senha;

    @NotNull private UsuarioPerfilEnum perfil;

    @NotNull private boolean isBloqueado;

    @NotNull private Long idEmpresa;

}
