package com.talent.model;

import com.talent.enums.ColaboradorStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.Column;
import java.util.UUID;

@Entity
@Data
@Table(name="COLABORADOR")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column @NotNull private String nome;
    @Column @NotNull private String matricula;
    @Column @NotNull private ColaboradorStatusEnum situacao;
    @Column @NotNull private String dataNascimento;
    @Column @NotNull private String dataAdmissao;
    @Column private String dataDesligamento;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
