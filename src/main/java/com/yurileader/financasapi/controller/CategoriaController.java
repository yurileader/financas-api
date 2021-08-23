package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.dto.CategoriaDTO;
import com.yurileader.financasapi.model.Categoria;
import com.yurileader.financasapi.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ModelMapper modelMapper;

    public CategoriaController(CategoriaService categoriaService, ModelMapper modelMapper) {
        this.categoriaService = categoriaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CategoriaDTO> listarTodos() {
        return toDtoList(categoriaService.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(categoriaService.buscarPorId(id)));
    }

    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        return modelMapper.map(categoriaDTO, Categoria.class);
    }

    public CategoriaDTO toDto(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    public List<CategoriaDTO> toDtoList(List<Categoria> categorias) {
        return categorias.stream()
                .map(categoria -> toDto(categoria))
                .collect(Collectors.toList());
    }
}
