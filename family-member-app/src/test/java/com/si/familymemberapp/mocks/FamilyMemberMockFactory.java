package com.si.familymemberapp.mocks;


import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.domain.FamilyMember;

import java.util.UUID;

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

    public static FamilyMember getFamilyMemberMock() {
        return FamilyMember.builder()
                .givenName("Adam")
                .familyName("Kowalski")
                .age(18)
                .familyId(UUID.randomUUID())
                .id(1L)
                .build();
    }
}
