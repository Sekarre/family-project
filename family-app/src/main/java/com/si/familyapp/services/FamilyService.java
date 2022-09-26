package com.si.familyapp.services;

import com.si.familyapp.DTO.FamilyDTO;

import java.util.UUID;

public interface FamilyService {

    UUID createFamily(FamilyDTO familyDTO);

    FamilyDTO getFamilyById(UUID id);
}
