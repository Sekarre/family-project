package com.si.familymemberapp.controllers;

import com.si.familymemberapp.DTO.FamilyMemberDTO;
import com.si.familymemberapp.services.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/family-members")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @PostMapping("/family/{familyId}")
    ResponseEntity<Long> createFamilyMember(@RequestBody @Valid FamilyMemberDTO familyMemberDTO, @PathVariable UUID familyId) {
        return ResponseEntity.ok(familyMemberService.createFamilyMember(familyMemberDTO, familyId));
    }

    @GetMapping("/family/{familyId}")
    List<FamilyMemberDTO> getFamilyMembers(@PathVariable UUID familyId) {
        return familyMemberService.getFamilyMembers(familyId);
    }
}
