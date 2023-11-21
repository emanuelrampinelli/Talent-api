package com.talent.model;

import com.talent.enums.ColaboradorStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="COLABORADOR")
public class Colaborador {

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column @NotNull
    private String nome;

    @Column @NotNull
    private String matricula;

    @Column @NotNull
    private ColaboradorStatusEnum situacao;

    @Column @NotNull
    private Date dataNascimento;

    @Column @NotNull
    private Date dataAdmissao;

    @Column
    private Date dataDesligamento;

    @OneToOne @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

    @ManyToOne @JoinColumn(name = "fk_cargo")
    private Cargo cargo;

    @OneToOne @JoinColumn(name = "fk_equipe")
    private Equipe equipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Colaborador that = (Colaborador) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

