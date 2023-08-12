package org.rent.circle.owner.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class OwnerInfoDtoTest {

    @Test
    public void OwnerInfoDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(OwnerInfoDto.class);

        // Assert
    }
}
