package com.si.familyapp.DTO;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.si.familyapp.validators.FamilyValidationMessageHolder.FAMILY_NAME_VALIDATION_MESSAGE;
import static com.si.familyapp.validators.FamilyValidationMessageHolder.GIVEN_NAME_VALIDATION_MESSAGE;
import static com.si.familyapp.validators.FamilyValidationRegexHolder.FAMILY_NAME_REGEX_PATTERN;
import static com.si.familyapp.validators.FamilyValidationRegexHolder.GIVEN_NAME_REGEX_PATTERN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FamilyMemberDTO {

    @Pattern(regexp = GIVEN_NAME_REGEX_PATTERN, message = GIVEN_NAME_VALIDATION_MESSAGE)
    @NotBlank
    private String givenName;

    @Pattern(regexp = FAMILY_NAME_REGEX_PATTERN, message = FAMILY_NAME_VALIDATION_MESSAGE)
    @NotBlank
    private String familyName;

    @Min(0)
    private Integer age;
}
