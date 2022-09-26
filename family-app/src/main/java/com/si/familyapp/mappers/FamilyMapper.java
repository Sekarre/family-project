package com.si.familyapp.mappers;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.domain.Family;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class FamilyMapper {

    public abstract Family mapFamilyDTOToFamily(FamilyDTO familyDTO);

    public abstract FamilyDTO mapFamilyToFamilyDTO(Family family);
}
