package com.si.familyapp.mappers;

import com.si.familyapp.DTO.FamilyDTO;
import com.si.familyapp.domain.Family;
import com.si.familyapp.testutil.JUnitMessageGenerator;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.si.familyapp.mocks.FamilyMockFactory.getFamilyDTOMock;
import static com.si.familyapp.mocks.FamilyMockFactory.getFamilyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FamilyMapperTest {

    private final FamilyMapper familyMapper = Mappers.getMapper(FamilyMapper.class);

    @Test
    public void should_map_FamilyDTO_to_Family() {
        JUnitMessageGenerator<?, ?> messageGenerator = new JUnitMessageGenerator<>(FamilyDTO.class, Family.class);

        //given
        final FamilyDTO from = getFamilyDTOMock();

        //when
        Family result = familyMapper.mapFamilyDTOToFamily(from);

        //then
        assertEquals(from.getFamilyName(), result.getFamilyName(),
                messageGenerator.getMessage("familyName", from.getFamilyName(), "familyName", result.getFamilyName()));
        assertEquals(from.getNrOfAdults(), result.getNrOfAdults(),
                messageGenerator.getMessage("nrOfAdults", from.getNrOfAdults(), "nrOfAdults", result.getNrOfAdults()));
        assertEquals(from.getNrOfChildren(), result.getNrOfChildren(),
                messageGenerator.getMessage("nrOfChildren", from.getNrOfChildren(), "nrOfChildren", result.getNrOfChildren()));
        assertEquals(from.getNrOfInfants(), result.getNrOfInfants(),
                messageGenerator.getMessage("nrOfInfants", from.getNrOfInfants(), "nrOfInfants", result.getNrOfInfants()));
    }

    @Test
    public void should_map_Family_to_FamilyDTO() {
        JUnitMessageGenerator<?, ?> messageGenerator = new JUnitMessageGenerator<>(Family.class, FamilyDTO.class);

        //given
        final Family from = getFamilyMock();

        //when
        FamilyDTO result = familyMapper.mapFamilyToFamilyDTO(from);

        //then
        assertEquals(from.getFamilyName(), result.getFamilyName(),
                messageGenerator.getMessage("familyName", from.getFamilyName(), "familyName", result.getFamilyName()));
        assertEquals(from.getNrOfAdults(), result.getNrOfAdults(),
                messageGenerator.getMessage("nrOfAdults", from.getNrOfAdults(), "nrOfAdults", result.getNrOfAdults()));
        assertEquals(from.getNrOfChildren(), result.getNrOfChildren(),
                messageGenerator.getMessage("nrOfChildren", from.getNrOfChildren(), "nrOfChildren", result.getNrOfChildren()));
        assertEquals(from.getNrOfInfants(), result.getNrOfInfants(),
                messageGenerator.getMessage("nrOfInfants", from.getNrOfInfants(), "nrOfInfants", result.getNrOfInfants()));
    }
}