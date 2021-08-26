package com.yurileader.financasapi.config.exceptionhandler;

import com.yurileader.financasapi.config.exceptionhandler.exceptions.PessoaAtivaException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class FinancasExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        var mensagemUsuario = messageSource.getMessage("recurso-nao-encontrado", null, LocaleContextHolder.getLocale());
        var mensagemDesenvolvedor = ex.getMessage();
        return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var mensagemUsuario = messageSource.getMessage("mensagem-invalida", null, LocaleContextHolder.getLocale());
        var mensagemDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({PessoaAtivaException.class})
    protected ResponseEntity<Object> handlePessoaAtivaException(PessoaAtivaException ex, WebRequest request) {
        var mensagemUsuario = messageSource.getMessage("pessoa-ativa", null, LocaleContextHolder.getLocale());
        var mensagemDesenvolvedor = ex.toString();
        return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        var mensagemUsuario = messageSource.getMessage("recurso-nao-permitido", null, LocaleContextHolder.getLocale());
        var mensagemDesenvolvedor = ExceptionUtils.getRootCause(ex).toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Erro> criarListaErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            var mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            var mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }
        return erros;
    }

    @Data
    @AllArgsConstructor
    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;
    }

}




