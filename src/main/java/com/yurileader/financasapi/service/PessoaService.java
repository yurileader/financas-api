package com.yurileader.financasapi.service;

import com.yurileader.financasapi.model.Pessoa;
import com.yurileader.financasapi.repository.PessoaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
