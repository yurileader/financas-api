package com.yurileader.financasapi.service;

import com.yurileader.financasapi.model.Categoria;
import com.yurileader.financasapi.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }
}
