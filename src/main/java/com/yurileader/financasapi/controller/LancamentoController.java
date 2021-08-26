package com.yurileader.financasapi.controller;

import com.yurileader.financasapi.config.event.RecursoCriadoEvent;
import com.yurileader.financasapi.dto.LancamentoDTO;
import com.yurileader.financasapi.dto.LancamentoDTOInput;
import com.yurileader.financasapi.model.Lancamento;
import com.yurileader.financasapi.service.LancamentoService;
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
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ApplicationEventPublisher publisher;

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @GetMapping
    public List<LancamentoDTO> listarTodos() {
        return toDtoList(lancamentoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(lancamentoService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<LancamentoDTOInput> cadastrar(@RequestBody @Valid LancamentoDTOInput lancamentoDTOInput, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.criar(toEntity(lancamentoDTOInput));
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(toDtoInput(lancamentoSalvo));
    }

    private LancamentoDTO toDto(Lancamento lancamento) {
        return modelMapper.map(lancamento, LancamentoDTO.class);
    }
    private LancamentoDTOInput toDtoInput(Lancamento lancamento) {
        return modelMapper.map(lancamento, LancamentoDTOInput.class);
    }
    private Lancamento toEntity(LancamentoDTOInput lancamentoDTOInput) {
        return modelMapper.map(lancamentoDTOInput, Lancamento.class);
    }
    private List<LancamentoDTO> toDtoList(List<Lancamento> lancamentos) {
        return lancamentos.stream().map(lancamento -> toDto(lancamento))
                .collect(Collectors.toList());
    }
}
