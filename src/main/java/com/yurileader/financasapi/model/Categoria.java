package com.yurileader.financasapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
