package com.si.familyapp.services.client;

import com.si.familyapp.exceptions.family.FamilyMemberCreateException;
import com.si.familyapp.exceptions.family.FamilyMemberGetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.si.familyapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FamilyFeignClientFallbackFactoryTest {

    private FamilyMemberClient familyMemberClient;

    @BeforeEach
    void setUp() {
        familyMemberClient = new FamilyFeignClientFallbackFactory().create(new Throwable());
    }

    @Test
    void should_throw_FamilyMemberCreateException_when_creating_family_member() {
        assertThrows(FamilyMemberCreateException.class, () -> familyMemberClient.createFamilyMember(getFamilyMemberDTOMock(), UUID.randomUUID()));
    }

    @Test
    void should_throw_FamilyMemberGetException_when_getting_family_members() {
        assertThrows(FamilyMemberGetException.class, () -> familyMemberClient.getFamilyMembers(UUID.randomUUID()));
    }
}