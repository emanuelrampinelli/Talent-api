package com.talent.model;

import com.talent.enums.TipoQuestionarioEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="MODELO_QUESTIONARIO")
public class ModeloQuestionario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column @NotNull
    private String nome;

    @Column @NotNull
    private TipoQuestionarioEnum tipo;

    @Column
    private String descricao;

    @ManyToOne @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;
}
