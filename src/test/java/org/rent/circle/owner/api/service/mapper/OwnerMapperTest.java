package org.rent.circle.owner.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.dto.OwnerDto;
import org.rent.circle.owner.api.dto.SaveOwnerInfoDto;
import org.rent.circle.owner.api.dto.UpdateOwnerInfoDto;
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
    public void toModel_WhenGivenAValidDto_ShouldMap() {
        // Arrange
        SaveOwnerInfoDto ownerInfo = SaveOwnerInfoDto.builder()
            .addressId(1L)
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
        assertEquals(ownerInfo.getAddressId(), result.getAddressId());
        assertEquals(ownerInfo.getFirstName(), result.getFirstName());
        assertEquals(ownerInfo.getLastName(), result.getLastName());
        assertEquals(ownerInfo.getMiddleName(), result.getMiddleName());
        assertEquals(ownerInfo.getSuffix().name(), result.getSuffix());
        assertEquals(ownerInfo.getEmail(), result.getEmail());
        assertEquals(ownerInfo.getPhone(), result.getPhone());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        OwnerDto result = ownerMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAnOwner_ShouldMap() {
        // Arrange
        Owner owner = new Owner();
        owner.setAddressId(1L);
        owner.setFirstName("First");
        owner.setLastName("Last");
        owner.setMiddleName("Middle");
        owner.setSuffix(Suffix.SR.name());
        owner.setEmail("myemail@email.com");
        owner.setPhone("1234567890");

        // Act
        OwnerDto result = ownerMapper.toDto(owner);

        // Assert
        assertNotNull(result);
        assertEquals(owner.getAddressId(), result.getAddressId());
        assertEquals(owner.getFirstName(), result.getFirstName());
        assertEquals(owner.getLastName(), result.getLastName());
        assertEquals(owner.getMiddleName(), result.getMiddleName());
        assertEquals(owner.getSuffix(), result.getSuffix().name());
        assertEquals(owner.getEmail(), result.getEmail());
        assertEquals(owner.getPhone(), result.getPhone());
    }

    @Test
    public void update_WhenGivenUpdatedOwnerInfoIsNull_ShouldNotUpdate() {
        // Arrange
        Owner owner = new Owner();
        owner.setAddressId(1L);
        owner.setFirstName("First");
        owner.setLastName("Last");
        owner.setMiddleName("Middle");
        owner.setSuffix(Suffix.SR.name());
        owner.setEmail("myemail@email.com");
        owner.setPhone("1234567890");

        // Act
        ownerMapper.update(null, owner);

        // Assert
        assertNotNull(owner);
        assertNotNull(owner.getAddressId());
        assertNotNull(owner.getFirstName());
        assertNotNull(owner.getLastName());
        assertNotNull(owner.getMiddleName());
        assertNotNull(owner.getSuffix());
        assertNotNull(owner.getEmail());
        assertNotNull(owner.getPhone());
    }

    @Test
    public void update_WhenGivenUpdatedOwnerInfo_ShouldUpdate() {
        // Arrange
        String email = "myemail@email.com";
        Owner owner = new Owner();
        owner.setAddressId(1L);
        owner.setFirstName("First");
        owner.setLastName("Last");
        owner.setMiddleName("Middle");
        owner.setSuffix(Suffix.SR.name());
        owner.setEmail(email);
        owner.setPhone("1234567890");

        UpdateOwnerInfoDto updateOwnerInfo = UpdateOwnerInfoDto.builder()
            .addressId(2L)
            .firstName("Updated")
            .lastName("Name")
            .middleName(null)
            .suffix(null)
            .phone("8901234567")
            .build();

        // Act
        ownerMapper.update(updateOwnerInfo, owner);

        // Assert
        assertEquals(owner.getAddressId(), updateOwnerInfo.getAddressId());
        assertEquals(owner.getFirstName(), updateOwnerInfo.getFirstName());
        assertEquals(owner.getLastName(), updateOwnerInfo.getLastName());
        assertEquals(owner.getMiddleName(), updateOwnerInfo.getMiddleName());
        assertEquals(owner.getSuffix(), updateOwnerInfo.getSuffix());
        assertEquals(email, owner.getEmail());
        assertEquals(owner.getPhone(), updateOwnerInfo.getPhone());
    }
}
