package org.rent.circle.owner.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.persistence.model.Owner;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class OwnerRepositoryTest {

    @Inject
    OwnerRepository ownerRepository;

    @Test
    @TestTransaction
    public void findByUserId_WhenOwnerDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Owner result = ownerRepository.findByUserId("invalid_user");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByUserId_WhenOwnerDoesExist_ShouldReturnOwner() {
        // Arrange

        // Act
        Owner result = ownerRepository.findByUserId("auth_user");

        // Assert
        assertNotNull(result);
    }
}
