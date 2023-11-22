package com.talent.model;

import com.talent.enums.CompetenciaEnum;
import com.talent.enums.EscalaLikertEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="PERGUNTA")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String texto;

    @Column
    private CompetenciaEnum competencia;

    @Column
    private EscalaLikertEnum escala;

    @ManyToOne @JoinColumn(name = "fk_modelo")
    private ModeloQuestionario modelo;
}
