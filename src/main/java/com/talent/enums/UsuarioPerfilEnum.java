package com.talent.enums;

public enum UsuarioPerfilEnum {

    ADMIN("ADMIN"),
    CONSULTOR("COLABORADOR");

    private String perfil;

    UsuarioPerfilEnum(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }
}
