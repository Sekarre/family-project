package com.si.familyapp.validators;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.DTO.FamilyMemberDTO;
import com.si.familyapp.exceptions.family.FamilyValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;

import static com.si.familyapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;
import static com.si.familyapp.mocks.FamilyMockFactory.getFamilyDTOMock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FamilyValidatorTest {

    @Test
    public void should_validate_family_and_return_true() {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        familyDTO.setFamilyMembers(new ArrayList<>());
        familyDTO.setNrOfAdults(0);
        familyDTO.setNrOfChildren(0);
        familyDTO.setNrOfInfants(0);

        //when
        boolean result = FamilyValidator.validateFamilyData(familyDTO);

        //then
        assertTrue(result);
    }

    @Test
    public void should_validate_family_and_throw_exception_for_incorrect_number_of_infants() {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        familyDTO.setFamilyMembers(new ArrayList<>());
        familyDTO.setNrOfInfants(2);

        //when + then
        assertThrows(FamilyValidationException.class, () -> FamilyValidator.validateFamilyData(familyDTO));
    }

    @Test
    public void should_validate_family_and_throw_exception_for_incorrect_number_of_children() {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        familyDTO.setFamilyMembers(new ArrayList<>());
        familyDTO.setNrOfChildren(2);

        //when + then
        assertThrows(FamilyValidationException.class, () -> FamilyValidator.validateFamilyData(familyDTO));
    }

    @Test
    public void should_validate_family_and_throw_exception_for_incorrect_number_of_adults() {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        familyDTO.setFamilyMembers(new ArrayList<>());
        familyDTO.setNrOfAdults(2);

        //when + then
        assertThrows(FamilyValidationException.class, () -> FamilyValidator.validateFamilyData(familyDTO));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    public void should_validate_family_and_throw_exception_for_correct_number_of_infants_but_incorrect_age(int age) {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        FamilyMemberDTO familyMemberDTOMock = getFamilyMemberDTOMock();
        familyMemberDTOMock.setAge(age);
        familyDTO.setFamilyMembers(Collections.singletonList(familyMemberDTOMock));
        familyDTO.setNrOfInfants(1);
        familyDTO.setNrOfAdults(0);
        familyDTO.setNrOfChildren(0);

        //when + then
        assertThrows(FamilyValidationException.class, () -> FamilyValidator.validateFamilyData(familyDTO));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3, 16})
    public void should_validate_family_and_throw_exception_for_correct_number_of_children_but_incorrect_age(int age) {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        FamilyMemberDTO familyMemberDTOMock = getFamilyMemberDTOMock();
        familyMemberDTOMock.setAge(age);
        familyDTO.setFamilyMembers(Collections.singletonList(familyMemberDTOMock));
        familyDTO.setNrOfInfants(0);
        familyDTO.setNrOfChildren(1);
        familyDTO.setNrOfAdults(0);

        //when + then
        assertThrows(FamilyValidationException.class, () -> FamilyValidator.validateFamilyData(familyDTO));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 15})
    public void should_validate_family_and_throw_exception_for_correct_number_of_adults_but_incorrect_age(int age) {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        FamilyMemberDTO familyMemberDTOMock = getFamilyMemberDTOMock();
        familyMemberDTOMock.setAge(age);
        familyDTO.setFamilyMembers(Collections.singletonList(familyMemberDTOMock));
        familyDTO.setNrOfInfants(0);
        familyDTO.setNrOfChildren(0);
        familyDTO.setNrOfAdults(1);

        //when + then
        assertThrows(FamilyValidationException.class, () -> FamilyValidator.validateFamilyData(familyDTO));
    }
}