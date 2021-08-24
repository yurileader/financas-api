package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.config.event.RecursoCriadoEvent;
import com.yurileader.financasapi.dto.PessoaDTO;
import com.yurileader.financasapi.dto.PessoaDTOInput;
import com.yurileader.financasapi.model.Pessoa;
import com.yurileader.financasapi.service.PessoaService;
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

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ApplicationEventPublisher publisher;
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

    @PostMapping
    public ResponseEntity<PessoaDTOInput> cadastrar(@RequestBody @Valid PessoaDTOInput pessoaDTOInput, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaService.criar(toEntity(pessoaDTOInput));
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(toDtoInput(pessoaSalva));
    }

    private PessoaDTO toDto(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    private PessoaDTOInput toDtoInput(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTOInput.class);
    }

    private Pessoa toEntity(PessoaDTOInput pessoaDTOInput) {
        return modelMapper.map(pessoaDTOInput, Pessoa.class);
    }

    private List<PessoaDTO> toDtoList(List<Pessoa> pessoas) {
        return pessoas.stream().map(pessoa -> toDto(pessoa)).collect(Collectors.toList());
    }
}
