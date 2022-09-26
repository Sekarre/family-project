package com.si.familymemberapp.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error(e.getMessage());
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : e.getBindingResult().getFieldErrors()) {
            final StringBuilder sb = new StringBuilder();
            sb.append(error.getField()).append(" ").append(error.getDefaultMessage());
            errors.add(sb.toString());
        }
        return new ResponseEntity<>(new ErrorMessage(errors, request.getRequestURL().toString()), HttpStatus.BAD_REQUEST);
    }
}
