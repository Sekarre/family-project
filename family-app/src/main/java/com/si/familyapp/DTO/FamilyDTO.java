package com.si.familyapp.DTO;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.si.familyapp.validators.FamilyValidationMessageHolder.FAMILY_NAME_VALIDATION_MESSAGE;
import static com.si.familyapp.validators.FamilyValidationRegexHolder.FAMILY_NAME_REGEX_PATTERN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class FamilyDTO {

    @Pattern(regexp = FAMILY_NAME_REGEX_PATTERN, message = FAMILY_NAME_VALIDATION_MESSAGE)
    @NotBlank
    private String familyName;

    @Min(value = 0)
    private Integer nrOfAdults;

    @Min(value = 0)
    private Integer nrOfChildren;

    @Min(value = 0)
    private Integer nrOfInfants;

    @Valid
    @NotEmpty
    private List<FamilyMemberDTO> familyMembers;
}
