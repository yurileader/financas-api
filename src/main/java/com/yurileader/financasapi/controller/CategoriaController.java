package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.model.Categoria;
import com.yurileader.financasapi.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> listarTodos(){
        return categoriaRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> listarPorId(@PathVariable Long id){
        return categoriaRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria)).orElse(ResponseEntity.notFound().build());
    }

}
