package com.si.familyapp.controllers;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.services.FamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/family")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    ResponseEntity<UUID> createFamily(@RequestBody @Valid FamilyDTO familyDTO) {
        return ResponseEntity.ok(familyService.createFamily(familyDTO));
    }

    @GetMapping("/{id}")
    ResponseEntity<FamilyDTO> createFamily(@PathVariable UUID id) {
        return ResponseEntity.ok(familyService.getFamilyById(id));
    }
}
