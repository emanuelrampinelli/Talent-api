package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

/**
 * Entidade que representa uma Instituicao.
 */
@Entity
@Data
@Table(name = "INSTITUICAO")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private Date dataCadastro;

    /**
     * Verifica se dois objetos Instituicao são iguais.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instituicao that = (Instituicao) o;
        return id != null && id.equals(that.id);
    }

    /**
     * Gera um código de hash único para o objeto Instituicao.
     *
     * @return Código de hash.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
