package com.yurileader.financasapi.service;

import com.yurileader.financasapi.config.exceptionhandler.exceptions.PessoaInativaOuInexistente;
import com.yurileader.financasapi.model.Lancamento;
import com.yurileader.financasapi.model.Pessoa;
import com.yurileader.financasapi.repository.LancamentoRepository;
import com.yurileader.financasapi.repository.PessoaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository, PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Page<Lancamento> buscarTodos(Pageable pageable) {
        return lancamentoRepository.findAll(pageable);
    }

    public Lancamento buscarPorId(Long id) {
        return lancamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Lancamento criar(Lancamento lancamento) {
        Pessoa pessoaSalva = pessoaRepository.findById(lancamento.getPessoa().getId()).orElseThrow(() -> new PessoaInativaOuInexistente());
        if (pessoaSalva.isInativo()) {
            throw new PessoaInativaOuInexistente();
        }
        return lancamentoRepository.save(lancamento);
    }
}
