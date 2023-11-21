package com.talent.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="CARGO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @Column @NotNull
    private String nome;

    @Column
    private String descricao;

    @NotNull @ManyToOne
    private Instituicao instituicao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cargo cargo = (Cargo) o;
        return id != null && Objects.equals(id, cargo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}