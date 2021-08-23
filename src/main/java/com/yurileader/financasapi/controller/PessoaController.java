package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.dto.PessoaDTO;
import com.yurileader.financasapi.model.Pessoa;
import com.yurileader.financasapi.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private ModelMapper modelMapper;
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<PessoaDTO> listarTodos() {
        return toDtoList(pessoaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(pessoaService.buscarPorId(id)));
    }

    private PessoaDTO toDto(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public List<PessoaDTO> toDtoList(List<Pessoa> pessoas) {
        return pessoas.stream().map(pessoa -> toDto(pessoa)).collect(Collectors.toList());
    }
}
