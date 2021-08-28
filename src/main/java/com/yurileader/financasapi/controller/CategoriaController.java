package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.config.event.RecursoCriadoEvent;
import com.yurileader.financasapi.dto.CategoriaDTO;
import com.yurileader.financasapi.model.Categoria;
import com.yurileader.financasapi.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ApplicationEventPublisher publisher;
    private final CategoriaService categoriaService;




    @GetMapping
    public List<CategoriaDTO> listarTodos() {
        return toDtoList(categoriaService.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(categoriaService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody @Valid CategoriaDTO categoriaDTO, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaService.criar(toEntity(categoriaDTO));
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(categoriaSalva));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        categoriaService.excluir(id);
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

