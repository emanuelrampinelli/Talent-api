package com.talent.model;

import com.talent.enums.QuestionarioStatusEnum;
import com.talent.enums.TipoQuestionarioEnum;
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
@Table(name="QUESTIONARIO")
public class Questionario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    private String pontosFortes;

    @Column
    private String pontosFracos;

    @Column
    @NotNull
    private QuestionarioStatusEnum status;

    @ManyToOne @JoinColumn(name = "fk_avaliador")
    private Colaborador avaliador;

    @ManyToOne @JoinColumn(name = "fk_avaliado")
    private Colaborador avaliado;

    @ManyToOne @JoinColumn(name = "fk_modelo")
    private ModeloQuestionario modelo;

}
