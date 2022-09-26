package com.si.familyapp.services;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.domain.Family;
import com.si.familyapp.exceptions.family.FamilyNotFoundException;
import com.si.familyapp.mappers.FamilyMapper;
import com.si.familyapp.repositories.FamilyRepository;
import com.si.familyapp.services.client.FamilyMemberClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.si.familyapp.validators.FamilyValidator.validateFamilyData;

@Slf4j
@RequiredArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyMapper familyMapper;
    private final FamilyMemberClient familyMemberClient;

    @Override
    public UUID createFamily(FamilyDTO familyDTO) {
        if (validateFamilyData(familyDTO)) {
            final Family family = familyMapper.mapFamilyDTOToFamily(familyDTO);
            final UUID id = UUID.randomUUID();
            family.setId(id);
            familyDTO.getFamilyMembers().forEach(fm -> familyMemberClient.createFamilyMember(fm, id));
            return familyRepository.save(family).getId();
        }
        throw new IllegalStateException("Cannot create family");
    }

    @Override
    public FamilyDTO getFamilyById(UUID id) {
        return familyRepository.findById(id)
                .map(familyMapper::mapFamilyToFamilyDTO)
                .map(familyDTO -> {
                    familyDTO.setFamilyMembers(familyMemberClient.getFamilyMembers(id));
                    return familyDTO;
                })
                .orElseThrow(() -> new FamilyNotFoundException("Family with id: " + id + " not found"));
    }
}
