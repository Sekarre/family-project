package com.si.familyapp.mocks;

import com.si.familyapp.DTO.FamilyMemberDTO;

public class FamilyMemberMockFactory {

    public static FamilyMemberDTO getFamilyMemberDTOMock() {
        return FamilyMemberDTO.builder()
                .givenName("Adam")
                .familyName("Kowalski")
                .age(18)
                .build();
    }

    public static FamilyMemberDTO getFamilyMemberDTOMock(Integer age) {
        return FamilyMemberDTO.builder()
                .givenName("Adam")
                .familyName("Kowalski")
                .age(age)
                .build();
    }
}
