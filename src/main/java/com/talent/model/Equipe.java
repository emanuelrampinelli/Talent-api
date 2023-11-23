package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entidade que representa uma Equipe.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "EQUIPE")
public class Equipe {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private String descricao;

    @OneToOne
    @JoinColumn(name = "fk_lider")
    private Colaborador lider;

    @ManyToMany(mappedBy = "equipes")
    private Set<Avaliacao> avaliacoes = new HashSet<>();

    /**
     * Verifica se dois objetos Equipe são iguais.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return id != null && id.equals(equipe.id);
    }

    /**
     * Gera um código de hash único para o objeto Equipe.
     *
     * @return Código de hash.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
