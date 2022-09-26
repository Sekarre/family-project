package com.si.familyapp.validators;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.DTO.FamilyMemberDTO;
import com.si.familyapp.exceptions.family.FamilyValidationException;

import java.util.List;

public class FamilyValidator {

    private static final int MIN_INFANTS_AGE = 0;
    private static final int MAX_INFANTS_AGE = 3;
    private static final int MIN_CHILDREN_AGE = 4;
    private static final int MAX_CHILDREN_AGE = 15;
    private static final int MIN_ADULTS_AGE = 16;
    private static final int MAX_ADULTS_AGE = 200;

    public static boolean validateFamilyData(FamilyDTO familyDTO) {
        ValidationResult validationResult = new ValidationResult();
        checkForNrOfInfants(familyDTO.getFamilyMembers(), familyDTO.getNrOfInfants(), validationResult);
        checkForNrOfChildren(familyDTO.getFamilyMembers(), familyDTO.getNrOfChildren(), validationResult);
        checkForNrOfAdults(familyDTO.getFamilyMembers(), familyDTO.getNrOfAdults(), validationResult);
        if (!validationResult.isValid()) {
            throw new FamilyValidationException(validationResult.getValidationErrors());
        }
        return true;
    }

    private static void checkForNrOfInfants(List<FamilyMemberDTO> familyMemberDTOS, Integer nrOfInfants, ValidationResult validationResult) {
        long actualNrOfInfants = getActualCount(familyMemberDTOS, MIN_INFANTS_AGE, MAX_INFANTS_AGE);
        if (nrOfInfants != actualNrOfInfants) {
            validationResult.addValidationError("nrOfInfants: " + nrOfInfants + " doesn't match actual number of infants: " + actualNrOfInfants);
        }
    }

    private static void checkForNrOfChildren(List<FamilyMemberDTO> familyMemberDTOS, Integer nrOfChildren, ValidationResult validationResult) {
        long actualNrOfChildren = getActualCount(familyMemberDTOS, MIN_CHILDREN_AGE, MAX_CHILDREN_AGE);
        if (nrOfChildren != actualNrOfChildren) {
            validationResult.addValidationError("nrOfChildren: " + nrOfChildren + " doesn't match actual number of children: " + actualNrOfChildren);
        }
    }

    private static void checkForNrOfAdults(List<FamilyMemberDTO> familyMemberDTOS, Integer nrOfAdults, ValidationResult validationResult) {
        long actualNrOfAdults = getActualCount(familyMemberDTOS, MIN_ADULTS_AGE, MAX_ADULTS_AGE);
        if (nrOfAdults != actualNrOfAdults) {
            validationResult.addValidationError("nrOfAdults: " + nrOfAdults + " doesn't match actual number of adults: " + actualNrOfAdults);
        }
    }

    private static long getActualCount(List<FamilyMemberDTO> familyMemberDTOS, int minAge, int maxAge) {
        return familyMemberDTOS.stream()
                .filter(fm -> isBetweenAgeRange(fm.getAge(), minAge, maxAge))
                .count();
    }

    private static boolean isBetweenAgeRange(Integer checkingAge, Integer minAge, Integer maxAge) {
        return checkingAge >= minAge && checkingAge <= maxAge;
    }
}
