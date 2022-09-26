package com.si.familyapp.exceptions.family;

public class FamilyNotFoundException extends RuntimeException {

    public FamilyNotFoundException(String message) {
        super(message);
    }
}
