package com.si.familymemberapp.services;

import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.mappers.FamilyMemberMapper;
import com.si.familymemberapp.repositories.FamilyMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    @Override
    public Long createFamilyMember(FamilyMemberDTO familyMemberDTO, UUID familyId) {
        return familyMemberRepository.save(familyMemberMapper.mapFamilyMemberDTOToFamilyMember(familyMemberDTO, familyId)).getId();
    }

    @Override
    public List<FamilyMemberDTO> getFamilyMembers(UUID familyId) {
        return familyMemberRepository.findAllByFamilyId(familyId).stream()
                .map(familyMemberMapper::mapFamilyMemberToFamilyMemberDTO)
                .collect(Collectors.toList());
    }
}
