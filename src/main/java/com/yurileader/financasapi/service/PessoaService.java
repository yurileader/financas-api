package com.yurileader.financasapi.service;

import com.yurileader.financasapi.config.exceptionhandler.exceptions.PessoaAtivaException;
import com.yurileader.financasapi.model.Pessoa;
import com.yurileader.financasapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;


    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void excluir(Long id) {
        Pessoa pessoaSalva = buscarPorId(id);
        if (pessoaSalva.isInativo()) {
            pessoaRepository.deleteById(id);
            return;
        }
        throw new PessoaAtivaException();
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPorId(id);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        pessoaRepository.save(pessoaSalva);
        return pessoaSalva;
    }

    public void atualizarAtivo(Long id, Boolean ativo) {
        Pessoa pessoaSalva = buscarPorId(id);
         if (ativo == null){
                     throw new IllegalArgumentException("Não é aceito valores null");
         }
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }
}
