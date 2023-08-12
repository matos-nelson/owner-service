package org.rent.circle.owner.api.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SuffixTest {

    @Test
    public void getValue_WhenGivenAInValidValue_ShouldReturnNull() {
        // Arrange

        // Act
        Suffix result = Suffix.getValue("invalid");

        // Assert
        assertNull(result);
    }

    @Test
    public void getValue_WhenCalled_ShouldReturnSuffix() {
        // Arrange

        // Act
        Suffix result = Suffix.getValue("Jr");

        // Assert
        assertEquals(Suffix.JR, result);
    }
}
