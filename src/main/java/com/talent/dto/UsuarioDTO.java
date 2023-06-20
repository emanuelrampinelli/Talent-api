package com.talent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@Data
public class UsuarioDTO {

    private UUID id;

    private String nome;

    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private Date dataCasdastro;

}