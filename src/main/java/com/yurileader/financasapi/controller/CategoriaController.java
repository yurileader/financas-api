package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.dto.CategoriaDTO;
import com.yurileader.financasapi.model.Categoria;
import com.yurileader.financasapi.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody CategoriaDTO categoriaDTO, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaService.criar(toEntity(categoriaDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoriaSalva.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(toDto(categoriaSalva));
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
