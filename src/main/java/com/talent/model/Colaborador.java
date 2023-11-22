package com.talent.model;

import com.talent.enums.ColaboradorStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidade que representa um Colaborador.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "COLABORADOR")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private String matricula;

    @Column
    @NotNull
    private ColaboradorStatusEnum situacao;

    @Column
    @NotNull
    private Date dataNascimento;

    @Column
    @NotNull
    private Date dataAdmissao;

    @Column
    private Date dataDesligamento;

    @OneToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name = "fk_cargo")
    private Cargo cargo;

    @OneToOne
    @JoinColumn(name = "fk_equipe")
    private Equipe equipe;

    /**
     * Verifica se dois objetos Colaborador são iguais.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Colaborador that = (Colaborador) o;
        return id != null && Objects.equals(id, that.id);
    }

    /**
     * Gera um código de hash único para o objeto Colaborador.
     *
     * @return Código de hash.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
