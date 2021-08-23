package com.yurileader.financasapi.config.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class FinancasExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {

        var mensgaemUsuario = messageSource.getMessage("recurso-nao-encontrado", null, LocaleContextHolder.getLocale());
        var mensagemDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(mensgaemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    private List<Erro> criarListaErros() {
        List<Erro> erros = new ArrayList<>();
        return erros;
    }

    @Data
    @AllArgsConstructor
    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;
    }
}

