package com.si.familymemberapp.mappers;

import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.domain.FamilyMember;
import com.si.familymemberapp.testutil.JUnitMessageGenerator;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static com.si.familymemberapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;
import static com.si.familymemberapp.mocks.FamilyMemberMockFactory.getFamilyMemberMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FamilyMemberMapperTest {

    private final FamilyMemberMapper familyMemberMapper = Mappers.getMapper(FamilyMemberMapper.class);

    @Test
    public void should_map_FamilyMemberDTO_to_FamilyMember() {
        JUnitMessageGenerator<?, ?> messageGenerator = new JUnitMessageGenerator<>(FamilyMemberDTO.class, FamilyMember.class);

        //given
        final FamilyMemberDTO from = getFamilyMemberDTOMock();
        final UUID familyId = UUID.randomUUID();

        //when
        FamilyMember result = familyMemberMapper.mapFamilyMemberDTOToFamilyMember(from, familyId);

        //then
        assertEquals(familyId, result.getFamilyId(),
                new JUnitMessageGenerator<>(null, FamilyMember.class).getMessage("familyId", familyId, "familyId", result.getFamilyId()));
        assertEquals(from.getFamilyName(), result.getFamilyName(),
                messageGenerator.getMessage("familyName", from.getFamilyName(), "familyName", result.getFamilyName()));
        assertEquals(from.getGivenName(), result.getGivenName(),
                messageGenerator.getMessage("givenName", from.getGivenName(), "givenName", result.getGivenName()));
        assertEquals(from.getAge(), result.getAge(),
                messageGenerator.getMessage("age", from.getAge(), "age", result.getAge()));
    }

    @Test
    public void should_map_FamilyMember_to_FamilyMemberDTO() {
        JUnitMessageGenerator<?, ?> messageGenerator = new JUnitMessageGenerator<>(FamilyMember.class, FamilyMemberDTO.class);

        //given
        final FamilyMember from = getFamilyMemberMock();

        //when
        FamilyMemberDTO result = familyMemberMapper.mapFamilyMemberToFamilyMemberDTO(from);

        //then
        assertEquals(from.getFamilyName(), result.getFamilyName(),
                messageGenerator.getMessage("familyName", from.getFamilyName(), "familyName", result.getFamilyName()));
        assertEquals(from.getGivenName(), result.getGivenName(),
                messageGenerator.getMessage("givenName", from.getGivenName(), "givenName", result.getGivenName()));
        assertEquals(from.getAge(), result.getAge(),
                messageGenerator.getMessage("age", from.getAge(), "age", result.getAge()));
    }
}