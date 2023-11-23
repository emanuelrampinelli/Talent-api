package com.talent.model;

import com.talent.enums.AlternativaEnum;
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
@Table(name="RESPOSTA")
public class Resposta {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String comentario;

    @Column
    @NotNull
    private AlternativaEnum alternatia;

    @ManyToOne @JoinColumn(name = "fk_pergunta")
    private Pergunta pergunta;

    @ManyToOne @JoinColumn(name = "fk_questionario")
    private Questionario questionario;
}
