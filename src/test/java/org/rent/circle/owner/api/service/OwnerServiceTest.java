package org.rent.circle.owner.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.dto.OwnerInfoDto;
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
        OwnerInfoDto ownerInfo = OwnerInfoDto.builder()
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
}
