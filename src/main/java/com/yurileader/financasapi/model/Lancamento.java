package com.yurileader.financasapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lancamento")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String descricao;

    @Column(name = "data_vencimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_categoria")
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_pessoa")
    private Pessoa pessoa;
}
