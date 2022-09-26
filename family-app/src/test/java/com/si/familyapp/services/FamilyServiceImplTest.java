package com.si.familyapp.services;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.DTO.FamilyMemberDTO;
import com.si.familyapp.domain.Family;
import com.si.familyapp.exceptions.family.FamilyNotFoundException;
import com.si.familyapp.mappers.FamilyMapper;
import com.si.familyapp.repositories.FamilyRepository;
import com.si.familyapp.services.client.FamilyMemberClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.si.familyapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;
import static com.si.familyapp.mocks.FamilyMockFactory.getFamilyDTOMock;
import static com.si.familyapp.mocks.FamilyMockFactory.getFamilyMock;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FamilyServiceImplTest {

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private FamilyMapper familyMapper;

    @Mock
    private FamilyMemberClient familyMemberClient;

    private FamilyService familyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        familyService = new FamilyServiceImpl(familyRepository, familyMapper, familyMemberClient);
    }

    @Test
    void should_create_family_and_return_id() {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        final Family family = getFamilyMock();
        when(familyMapper.mapFamilyDTOToFamily(any(FamilyDTO.class))).thenReturn(family);
        when(familyRepository.save(any(Family.class))).thenReturn(family);

        //when
        UUID result = familyService.createFamily(familyDTO);

        //then
        assertNotNull(result);
        verify(familyMapper, times(1)).mapFamilyDTOToFamily(familyDTO);
        verify(familyRepository, times(1)).save(family);
        verify(familyMemberClient, times(familyDTO.getFamilyMembers().size())).createFamilyMember(any(FamilyMemberDTO.class), any(UUID.class));
    }

    @Test
    void should_get_family_by_id_and_return_family_data() {
        //given
        final UUID id = UUID.randomUUID();
        final FamilyDTO familyDTO = getFamilyDTOMock();
        final Family family = getFamilyMock();
        final List<FamilyMemberDTO> familyMemberDTOs = Collections.singletonList(getFamilyMemberDTOMock());
        when(familyRepository.findById(any(UUID.class))).thenReturn(ofNullable(family));
        when(familyMapper.mapFamilyToFamilyDTO(any(Family.class))).thenReturn(familyDTO);
        when(familyMemberClient.getFamilyMembers(any(UUID.class))).thenReturn(familyMemberDTOs);

        //when
        FamilyDTO result = familyService.getFamilyById(id);

        //then
        assertNotNull(result);
        assertEquals(result.getFamilyMembers(), familyMemberDTOs);
        verify(familyRepository, times(1)).findById(id);
        verify(familyMapper, times(1)).mapFamilyToFamilyDTO(family);
        verify(familyMemberClient, times(1)).getFamilyMembers(id);
    }

    @Test
    void should_NOT_get_family_by_id_and_throw_exception() {
        //given
        final UUID id = UUID.randomUUID();
        when(familyRepository.findById(any(UUID.class))).thenReturn(empty());

        //when
        assertThrows(FamilyNotFoundException.class, () -> familyService.getFamilyById(id));

        //then
        verify(familyRepository, times(1)).findById(id);
        verify(familyMapper, times(0)).mapFamilyToFamilyDTO(any(Family.class));
        verify(familyMemberClient, times(0)).getFamilyMembers(any(UUID.class));
    }
}