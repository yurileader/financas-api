package com.yurileader.financasapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate dataPagamento;
    @NotNull
    private BigDecimal valor;
    private String observacao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoLancamento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_categoria")
    @NotNull
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_pessoa")
    @NotNull
    private Pessoa pessoa;
}
