package com.talent.enums;

public enum UsuarioPerfilEnum {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_COLABORADOR("ROLE_COLABORADOR");

    private final String perfil;

    UsuarioPerfilEnum(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }
}
