package com.yurileader.financasapi.service;

import com.yurileader.financasapi.model.Categoria;
import com.yurileader.financasapi.repository.CategoriaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }


    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
