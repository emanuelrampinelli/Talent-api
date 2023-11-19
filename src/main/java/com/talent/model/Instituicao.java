package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Date;
import java.util.UUID;


@Entity
@Data
@Table(name = "INSTITUICAO")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column @NotNull
    private String nome;

    @Column @NotNull
    private Date dataCadastro;
}
