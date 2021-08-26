package com.yurileader.financasapi.dto;

import com.yurileader.financasapi.model.TipoLancamento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LancamentoDTOInput {

    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String observacao;
    private TipoLancamento tipo;
    private CategoriaDTO categoria;
    private PessoaDTOInput pessoa;
}
