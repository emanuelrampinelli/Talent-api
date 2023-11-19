package com.talent.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${springdoc.api-docs.titulo}")
    private String titulo;

    @Value("${springdoc.api-docs.descricao}")
    private String descricao;

    @Value("${springdoc.api-docs.versao-api}")
    private String versao;

    private static final String NOME_CONTATO ="Datahub - Big Data & Analytics";

    private static final String URL_CONTATO ="https://www.datahub.com.br";

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setUrl(URL_CONTATO);
        contact.setName(NOME_CONTATO);

        Info info = new Info()
                .title(titulo)
                .version(versao)
                .description(descricao)
                .contact(contact);

        return new OpenAPI().info(info);
    }
}