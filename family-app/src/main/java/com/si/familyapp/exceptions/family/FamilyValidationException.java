package com.si.familyapp.exceptions.family;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FamilyValidationException extends RuntimeException {

    private List<String> errors = new ArrayList<>();

    public FamilyValidationException(String message) {
        super(message);
    }

    public FamilyValidationException(List<String> errors) {
        this.errors = errors;
    }
}
