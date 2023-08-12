package org.rent.circle.owner.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.dto.OwnerInfoDto;
import org.rent.circle.owner.api.enums.Suffix;
import org.rent.circle.owner.api.persistence.model.Owner;

@QuarkusTest
public class OwnerMapperTest {

    @Inject
    OwnerMapper ownerMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Owner result = ownerMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenAnOwnerInfoDto_ShouldMap() {
        // Arrange
        OwnerInfoDto ownerInfo = OwnerInfoDto.builder()
            .firstName("First")
            .lastName("Last")
            .middleName("Middle")
            .suffix(Suffix.SR)
            .email("myemail@email.com")
            .phone("1234567890")
            .build();

        // Act
        Owner result = ownerMapper.toModel(ownerInfo);

        // Assert
        assertNotNull(result);
        assertEquals(ownerInfo.getFirstName(), result.getFirstName());
        assertEquals(ownerInfo.getLastName(), result.getLastName());
        assertEquals(ownerInfo.getMiddleName(), result.getMiddleName());
        assertEquals(ownerInfo.getSuffix().name(), result.getSuffix());
        assertEquals(ownerInfo.getEmail(), result.getEmail());
        assertEquals(ownerInfo.getPhone(), result.getPhone());
    }
}
