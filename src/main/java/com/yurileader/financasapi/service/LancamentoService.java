package com.yurileader.financasapi.service;

import com.yurileader.financasapi.model.Lancamento;
import com.yurileader.financasapi.repository.LancamentoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public List<Lancamento> buscarTodos() {
        return lancamentoRepository.findAll();
    }

    public Lancamento buscarPorId(Long id) {
        return lancamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Lancamento criar(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }
}
