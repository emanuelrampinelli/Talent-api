package com.talent.enums;

public enum MensagemResponseEnum {

    // Autenticação do Usuário
    AUTENTICACAO_SUCESSO("Autenticação realizada com sucesso."),
    AUTENTICACAO_FALHA("Falha ao realizar autenticação."),
    EMAIL_JA_CADASTRADO("E-mail já cadastrado."),
    CADASTRO_FALHA("Falha ao cadastrar."),
    CADASTRO_SUCESSO("Cadastro realizado com sucesso."),

    // Cliente
    CLIENTE_INVALIDO("Cliente inválido."),
    FALHA_LISTAGEM_CLIENTES("Falha ao carregar lista de clientes."),
    SUCESSO_LISTAGEM_CLIENTES("Sucesso ao carregar lista de clientes."),

    // Consumo
    ANO_INVALIDO("Ano inválido."),
    MES_INVALIDO("Mês inválido."),
    SUCESSO_CALCULO_CONSUMO("Sucesso ao calcular consumo."),
    FALHA_CONSULTA_MES("Falha na consulta por mês."),
    SUCESSO_TIPO_CONSUMO("Sucesso ao carregar tipos de consumo."),
    SEM_CONSUMO("Não foram encontrados registros de consumo."),

    // Ambiente
    SUCESSO_LISTA_AMBIENTE("Sucesso ao carregar lista de ambientes.");

    private final String value;

    MensagemResponseEnum(String value) {
        this.value = value;
    }

    public String getValue(){
         return value;
    }
}
