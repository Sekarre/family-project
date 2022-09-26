package com.si.familymemberapp.controllers;

import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.services.FamilyMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static com.si.familymemberapp.mocks.FamilyMemberMockFactory.getFamilyMemberDTOMock;
import static com.si.familymemberapp.testutil.TestUtil.convertObjectToJsonBytes;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FamilyMemberControllerTest {

    @Mock
    private FamilyMemberService familyMemberService;
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api/v1/family-members/family/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        FamilyMemberController familyMemberController = new FamilyMemberController(familyMemberService);

        mockMvc = MockMvcBuilders.standaloneSetup(familyMemberController).build();
    }

    @Test
    void should_return_family_with_OK_status() throws Exception {
        //given
        final FamilyMemberDTO familyMemberDTO = getFamilyMemberDTOMock();
        final UUID familyId = UUID.randomUUID();
        when(familyMemberService.getFamilyMembers(any(UUID.class))).thenReturn(Collections.singletonList(familyMemberDTO));

        //when + then
        mockMvc.perform(get(BASE_URL + familyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].familyName").value(familyMemberDTO.getFamilyName()))
                .andExpect(jsonPath("$[0].givenName").value(familyMemberDTO.getGivenName()))
                .andExpect(jsonPath("$[0].age").value(familyMemberDTO.getAge()));

        verify(familyMemberService, times(1)).getFamilyMembers(familyId);
    }

    @Test
    void should_create_family_member_and_return_id_with_OK_status() throws Exception {
        //given
        final FamilyMemberDTO familyMemberDTO = getFamilyMemberDTOMock();
        byte[] content = convertObjectToJsonBytes(familyMemberDTO);
        final UUID familyId = UUID.randomUUID();
        final Long returnId = 1L;
        when(familyMemberService.createFamilyMember(any(FamilyMemberDTO.class), any(UUID.class))).thenReturn(returnId);

        //when + then
        mockMvc.perform(post(BASE_URL + familyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(returnId.toString()));

        verify(familyMemberService, times(1)).createFamilyMember(familyMemberDTO, familyId);
    }
}