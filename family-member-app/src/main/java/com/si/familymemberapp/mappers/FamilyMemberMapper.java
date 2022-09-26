package com.si.familymemberapp.mappers;

import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.domain.FamilyMember;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class FamilyMemberMapper {

    @Mapping(source = "familyId", target = "familyId")
    public abstract FamilyMember mapFamilyMemberDTOToFamilyMember(FamilyMemberDTO familyDTO, UUID familyId);

    public abstract FamilyMemberDTO mapFamilyMemberToFamilyMemberDTO(FamilyMember familyMember);
}
