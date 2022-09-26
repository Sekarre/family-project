package com.si.familyapp.controllers;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.services.FamilyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.si.familyapp.mocks.FamilyMockFactory.getFamilyDTOMock;
import static com.si.familyapp.testutil.TestUtil.convertObjectToJsonBytes;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FamilyControllerTest {

    @Mock
    private FamilyService familyService;
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api/v1/family/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        FamilyController familyController = new FamilyController(familyService);

        mockMvc = MockMvcBuilders.standaloneSetup(familyController).build();
    }

    @Test
    void should_return_family_with_OK_status() throws Exception {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        final UUID id = UUID.randomUUID();
        when(familyService.getFamilyById(any(UUID.class))).thenReturn(familyDTO);

        //when + then
        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.familyName").value(familyDTO.getFamilyName()))
                .andExpect(jsonPath("$.nrOfAdults").value(familyDTO.getNrOfAdults()))
                .andExpect(jsonPath("$.nrOfChildren").value(familyDTO.getNrOfChildren()))
                .andExpect(jsonPath("$.nrOfInfants").value(familyDTO.getNrOfInfants()))
                .andExpect(jsonPath("$.familyMembers").isArray());

        verify(familyService, times(1)).getFamilyById(id);
    }

    @Test
    void should_create_family_and_return_id_with_OK_status() throws Exception {
        //given
        final FamilyDTO familyDTO = getFamilyDTOMock();
        byte[] content = convertObjectToJsonBytes(familyDTO);
        final UUID id = UUID.randomUUID();
        when(familyService.createFamily(any(FamilyDTO.class))).thenReturn(id);

        //when + then
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(id.toString()));

        verify(familyService, times(1)).createFamily(familyDTO);
    }
}