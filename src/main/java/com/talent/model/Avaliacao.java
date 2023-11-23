package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String titulo;

    @Column
    @NotNull
    private Date dataInicio;

    @Column
    private Date dataTermino;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_modelo_autoavaliacao")
    private ModeloQuestionario modeloAutoAvaliacao;

    @ManyToOne
    @JoinColumn(name = "fk_modelo_interface")
    private ModeloQuestionario modeloInterface;

    @ManyToOne
    @JoinColumn(name = "fk_modelo_supervisor")
    private ModeloQuestionario modeloSupervisor;

    @ManyToOne
    @JoinColumn(name = "fk_modelo_liderado")
    private ModeloQuestionario modeloLiderado;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

    @ManyToMany
    @JoinTable(
            name = "avaliacao_equipe",
            joinColumns = @JoinColumn(name = "avaliacao_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    private Set<Equipe> equipes = new HashSet<>();

}