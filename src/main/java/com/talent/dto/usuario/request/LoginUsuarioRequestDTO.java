package com.talent.dto.usuario.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUsuarioRequestDTO {

    @NotNull private String email;
    @NotNull private String senha;
}
