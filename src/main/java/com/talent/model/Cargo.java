package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

/**
 * Entidade que representa um Cargo.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CARGO")
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

    /**
     * Verifica se dois objetos Cargo são iguais.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cargo cargo = (Cargo) o;
        return id != null && Objects.equals(id, cargo.id);
    }

    /**
     * Gera um código de hash único para o objeto Cargo.
     *
     * @return Código de hash.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
