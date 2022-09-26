package com.si.familyapp.exceptions.handler;

import com.si.familyapp.exceptions.BadRequestException;
import com.si.familyapp.exceptions.family.FamilyMemberCreateException;
import com.si.familyapp.exceptions.family.FamilyNotFoundException;
import com.si.familyapp.exceptions.family.FamilyValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @ExceptionHandler(value = FamilyMemberCreateException.class)
    public ResponseEntity<ErrorMessage> handleFamilyMemberCreateException(FamilyMemberCreateException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(e.getLocalizedMessage(), request.getRequestURL().toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FamilyNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleFamilyNotFoundException(FamilyNotFoundException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(e.getLocalizedMessage(), request.getRequestURL().toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FamilyValidationException.class)
    public ResponseEntity<ErrorMessage> handleFamilyValidationException(FamilyValidationException e, HttpServletRequest request) {
        log.error(StringUtils.join(e.getErrors(), "; "));
        return new ResponseEntity<>(new ErrorMessage(e.getErrors(), request.getRequestURL().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(e.getLocalizedMessage(), request.getRequestURL().toString()), HttpStatus.BAD_REQUEST);
    }

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
