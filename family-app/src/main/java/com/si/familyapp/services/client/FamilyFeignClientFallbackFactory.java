package com.si.familyapp.services.client;

import com.si.familyapp.DTO.FamilyMemberDTO;
import com.si.familyapp.exceptions.BadRequestException;
import com.si.familyapp.exceptions.family.FamilyMemberCreateException;
import com.si.familyapp.exceptions.family.FamilyMemberGetException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FamilyFeignClientFallbackFactory implements FallbackFactory<FamilyMemberClient> {

    @Override
    public FamilyMemberClient create(Throwable cause) {
        return new FamilyMemberClient() {
            @Override
            public void createFamilyMember(FamilyMemberDTO familyMemberDTO, UUID familyId) {
                String errorMessage = "Couldn't create family member: " + familyMemberDTO.getFamilyName() + " "
                        + familyMemberDTO.getGivenName() + " for family with id: " + familyId;
                if (cause instanceof BadRequestException) {
                    throw new BadRequestException(cause.getLocalizedMessage());
                }
                throw new FamilyMemberCreateException(errorMessage);
            }

            @Override
            public List<FamilyMemberDTO> getFamilyMembers(UUID familyId) {
                String errorMessage = "Couldn't retrieve family members for family with id: " + familyId;
                if (cause instanceof BadRequestException) {
                    throw new BadRequestException(errorMessage);
                }
                throw new FamilyMemberGetException(errorMessage);
            }
        };
    }
}
