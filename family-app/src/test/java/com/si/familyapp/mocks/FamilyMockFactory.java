package com.si.familyapp.mocks;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.domain.Family;

import java.util.Collections;
import java.util.UUID;

import static com.si.familyapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;

public class FamilyMockFactory {

    public static FamilyDTO getFamilyDTOMock() {
        return FamilyDTO.builder()
                .familyName("Kowalski")
                .nrOfAdults(1)
                .nrOfChildren(0)
                .nrOfInfants(0)
                .familyMembers(Collections.singletonList(getFamilyMemberDTOMock()))
                .build();
    }

    public static Family getFamilyMock() {
        return Family.builder()
                .id(UUID.randomUUID())
                .familyName("Kowalski")
                .nrOfAdults(1)
                .nrOfChildren(0)
                .nrOfInfants(0)
                .build();
    }
}
