package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name="CARGO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String nome;

    @Column
    private String descricao;

    @NotNull
    @ManyToOne
    private Instituicao instituicao;
}