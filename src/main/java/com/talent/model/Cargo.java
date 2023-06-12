package com.talent.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="CARGO")
@Data
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;
}