package com.talent.enums;

public enum MensagemResponseEnum {

    // Autenticação do Usuário
    AUTENTICACAO_SUCESSO("Autenticação realizada com sucesso."),
    AUTENTICACAO_FALHA("Falha ao realizar autenticação."),
    EMAIL_JA_CADASTRADO("E-mail já cadastrado."),
    CADASTRO_FALHA("Falha ao cadastrar."),
    CADASTRO_SUCESSO("Cadastro realizado com sucesso.");

    private final String value;

    MensagemResponseEnum(String value) {
        this.value = value;
    }

    public String getValue(){
         return value;
    }
}
