package com.si.familymemberapp.services;

import com.si.familymemberapp.DTO.FamilyMemberDTO;

import java.util.List;
import java.util.UUID;

public interface FamilyMemberService {

    Long createFamilyMember(FamilyMemberDTO familyMemberDTO, UUID familyId);

    List<FamilyMemberDTO> getFamilyMembers(UUID familyId);
}
