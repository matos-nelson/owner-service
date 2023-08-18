package org.rent.circle.owner.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.dto.OwnerDto;
import org.rent.circle.owner.api.dto.SaveOwnerInfoDto;
import org.rent.circle.owner.api.dto.UpdateOwnerInfoDto;
import org.rent.circle.owner.api.enums.Suffix;
import org.rent.circle.owner.api.persistence.model.Owner;
import org.rent.circle.owner.api.persistence.repository.OwnerRepository;
import org.rent.circle.owner.api.service.mapper.OwnerMapper;

@QuarkusTest
public class OwnerServiceTest {

    @InjectMock
    OwnerMapper ownerMapper;

    @InjectMock
    OwnerRepository ownerRepository;

    @Inject
    OwnerService ownerService;

    @Test
    public void saveInfo_WhenCalled_ShouldReturnSavedOwnerId() {
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

        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerMapper.toModel(ownerInfo)).thenReturn(owner);

        // Act
        Long result = ownerService.saveInfo(ownerInfo);

        // Assert
        assertEquals(owner.getId(), result);
        verify(ownerRepository, times(1)).persist(owner);
    }

    @Test
    public void getOwnerInfo_WhenCalled_ShouldReturnOwner() {
        // Arrange
        Long ownerId = 1L;
        Owner owner = new Owner();
        owner.setId(1L);

        OwnerDto ownerDto = OwnerDto.builder()
            .firstName("First")
            .lastName("Last")
            .middleName("Middle")
            .suffix(Suffix.SR)
            .email("myemail@email.com")
            .phone("1234567890")
            .build();

        when(ownerRepository.findById(ownerId)).thenReturn(owner);
        when(ownerMapper.toDto(owner)).thenReturn(ownerDto);

        // Act
        OwnerDto result = ownerService.getOwnerInfo(ownerId);

        // Assert
        assertNotNull(result);
        assertEquals(ownerDto, result);
    }

    @Test
    public void getOwnerInfo_WhenOwnerIsNotFound_ShouldReturnNull() {
        // Arrange
        Long ownerId = 1L;

        when(ownerRepository.findById(ownerId)).thenReturn(null);
        when(ownerMapper.toDto(null)).thenReturn(null);

        // Act
        OwnerDto result = ownerService.getOwnerInfo(ownerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void updateOwnerInfo_WhenOwnerIsNotFound_ShouldReturnNotUpdate() {
        // Arrange
        Long ownerId = 1L;
        UpdateOwnerInfoDto updateOwnerInfo = UpdateOwnerInfoDto.builder().build();
        when(ownerRepository.findById(ownerId)).thenReturn(null);

        // Act
        ownerService.updateOwnerInfo(ownerId, updateOwnerInfo);

        // Assert
        verify(ownerMapper, never()).update(updateOwnerInfo, null);
    }

    @Test
    public void updateOwnerInfo_WhenCalled_ShouldUpdate() {
        // Arrange
        Long ownerId = 1L;

        Owner owner = new Owner();
        owner.setId(ownerId);

        UpdateOwnerInfoDto updateOwnerInfo = UpdateOwnerInfoDto.builder()
            .addressId(2L)
            .firstName("Updated")
            .lastName("Name")
            .middleName(null)
            .suffix(null)
            .phone("8901234567")
            .build();
        when(ownerRepository.findById(ownerId)).thenReturn(owner);

        // Act
        ownerService.updateOwnerInfo(ownerId, updateOwnerInfo);

        // Assert
        verify(ownerMapper, times(1)).update(updateOwnerInfo, owner);
    }
}
