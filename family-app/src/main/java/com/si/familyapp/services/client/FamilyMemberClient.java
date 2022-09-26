package com.si.familyapp.services.client;

import com.si.familyapp.DTO.FamilyMemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "${feign.client.familymember.name}",
        fallbackFactory = FamilyFeignClientFallbackFactory.class
)
public interface FamilyMemberClient {

    @PostMapping("/api/v1/family-members/family/{familyId}")
    void createFamilyMember(FamilyMemberDTO familyMemberDTO, @PathVariable UUID familyId);

    @GetMapping("/api/v1/family-members/family/{familyId}")
    List<FamilyMemberDTO> getFamilyMembers(@PathVariable UUID familyId);
}
