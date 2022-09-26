package com.si.familymemberapp.services;

import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.domain.FamilyMember;
import com.si.familymemberapp.mappers.FamilyMemberMapper;
import com.si.familymemberapp.repositories.FamilyMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.si.familymemberapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;
import static com.si.familymemberapp.mocks.FamilyMemberMockFactory.getFamilyMemberMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FamilyMemberServiceImplTest {

    @Mock
    private FamilyMemberRepository familyMemberRepository;

    @Mock
    private FamilyMemberMapper familyMemberMapper;

    private FamilyMemberService familyMemberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        familyMemberService = new FamilyMemberServiceImpl(familyMemberRepository, familyMemberMapper);
    }

    @Test
    void should_create_family_member_and_return_id() {
        //given
        final FamilyMemberDTO familyMemberDTO = getFamilyMemberDTOMock();
        final UUID familyId = UUID.randomUUID();
        final FamilyMember familyMember = getFamilyMemberMock();
        familyMember.setFamilyId(null);
        when(familyMemberMapper.mapFamilyMemberDTOToFamilyMember(any(FamilyMemberDTO.class), any(UUID.class))).thenReturn(familyMember);
        when(familyMemberRepository.save(any(FamilyMember.class))).thenReturn(familyMember);

        //when
        Long result = familyMemberService.createFamilyMember(familyMemberDTO, familyId);

        //then
        assertNotNull(result);
        verify(familyMemberMapper, times(1)).mapFamilyMemberDTOToFamilyMember(familyMemberDTO, familyId);
        verify(familyMemberRepository, times(1)).save(familyMember);
    }

    @Test
    void should_get_all_family_members_by_family_id() {
        //given
        final FamilyMember familyMemberMock = getFamilyMemberMock();
        final UUID familyId = UUID.randomUUID();
        final FamilyMemberDTO familyMemberDTOMock = getFamilyMemberDTOMock();
        final List<FamilyMember> familyMembers = Collections.singletonList(familyMemberMock);
        when(familyMemberRepository.findAllByFamilyId(any(UUID.class))).thenReturn(familyMembers);
        when(familyMemberMapper.mapFamilyMemberToFamilyMemberDTO(any(FamilyMember.class))).thenReturn(familyMemberDTOMock);

        //when
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMembers(familyId);

        //then
        assertNotNull(result);
        assertEquals(result.get(0), familyMemberDTOMock);
        verify(familyMemberMapper, times(1)).mapFamilyMemberToFamilyMemberDTO(familyMemberMock);
        verify(familyMemberRepository, times(1)).findAllByFamilyId(familyId);
    }
}