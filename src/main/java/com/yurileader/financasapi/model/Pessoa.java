package com.yurileader.financasapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    @Transient
    public boolean isInativo() {
        return !ativo;
    }
}
