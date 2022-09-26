package com.si.familyapp.validators;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ValidationResult {

    private List<String> validationErrors = new ArrayList<>();

    public void addValidationError(String validationError) {
        validationErrors.add(validationError);
    }

    public boolean isValid() {
        return validationErrors.isEmpty();
    }
}
