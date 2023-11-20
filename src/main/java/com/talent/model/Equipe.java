package com.talent.model;

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
@Table(name="EQUIPE")
public class Equipe {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;
    @Column @NotNull
    private String nome;
    @Column @NotNull
    private String descricao;
    @OneToOne @JoinColumn(name = "fk_lider")
    private Colaborador lider;

}
