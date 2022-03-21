package com.example.cadastrodecliente.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
Nesse metodo é definida que todos os Erros de requisição será enviado 
uma mensagem e o nome do campo com determinados erros.
O metodo Retorna uma Lista com todos esses Erros.
*/

@RestControllerAdvice
public class ErroValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormDto> handle(MethodArgumentNotValidException exception) {

        // Lista que é devolvida para o usuario (campo, mensagem)
        List<ErroFormDto> erroFormDto = new ArrayList<>();

        // Lista que encontra todos os erros que o Spring encontrou
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        // Percorre todos erros e add na Lista somente o que é necessarios dos erros (campo, mensagem)
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormDto erro = new ErroFormDto(e.getField(), mensagem);
            erroFormDto.add(erro);
        });

        return erroFormDto;
    }

}
