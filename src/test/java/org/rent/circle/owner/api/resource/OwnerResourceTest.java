package org.rent.circle.owner.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.dto.SaveOwnerInfoDto;
import org.rent.circle.owner.api.enums.Suffix;

@QuarkusTest
@TestHTTPEndpoint(OwnerResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class OwnerResourceTest {

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedOwnerId() {
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
        // Assert
        given()
            .contentType("application/json")
            .body(ownerInfo)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidRequestToSave_ShouldReturnBadRequest() {
        // Arrange
        SaveOwnerInfoDto ownerInfo = SaveOwnerInfoDto.builder()
            .addressId(1L)
            .firstName("First")
            .lastName("Last")
            .middleName("Middle")
            .suffix(Suffix.SR)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(ownerInfo)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void GET_WhenAnOwnerCantBeFound_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/1")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenOwnerIsFound_ShouldReturnOwner() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("addressId", is(200),
                "firstName", is("First"),
                "lastName", is("Last"),
                "middleName", is(nullValue()),
                "suffix", is(nullValue()),
                "email", is("first.last@email.com"),
                "phone", is("1234567890")
            );
    }
}
