package org.rent.circle.owner.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.annotation.AuthUser;
import org.rent.circle.owner.api.dto.SaveOwnerInfoDto;
import org.rent.circle.owner.api.dto.UpdateOwnerInfoDto;
import org.rent.circle.owner.api.enums.Suffix;

@QuarkusTest
@TestHTTPEndpoint(OwnerResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@AuthUser
public class OwnerResourceTest {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "update_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "123456")
    })
    public @interface UpdateOwnerUser {

    }

    @Test
    public void POST_WhenGivenAValidRequestToSave_ShouldReturnSavedOwnerId() {
        // Arrange
        SaveOwnerInfoDto ownerInfo = SaveOwnerInfoDto.builder()
            .addressId(1L)
            .userId("123")
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
            .userId("123")
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

    @Test
    @UpdateOwnerUser
    public void PUT_WhenGivenAnInValidRequestToUpdate_ShouldReturnBadRequest() {
        // Arrange
        UpdateOwnerInfoDto updateOwnerInfo = UpdateOwnerInfoDto.builder()
            .addressId(null)
            .firstName("UFirst")
            .lastName("ULast")
            .middleName(null)
            .suffix(null)
            .phone("1234567890")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateOwnerInfo)
            .when()
            .put()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @UpdateOwnerUser
    public void PUT_WhenAnOwnerCantBeFound_ShouldReturnNoContent() {
        // Arrange
        UpdateOwnerInfoDto updateOwnerInfo = UpdateOwnerInfoDto.builder()
            .addressId(2L)
            .firstName("UFirst")
            .lastName("ULast")
            .middleName(null)
            .suffix(null)
            .phone("1234567890")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateOwnerInfo)
            .when()
            .put()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @UpdateOwnerUser
    public void PUT_WheGivenAValidUpdateRequest_ShouldReturnNoContent() {
        // Arrange
        UpdateOwnerInfoDto updateOwnerInfo = UpdateOwnerInfoDto.builder()
            .addressId(2L)
            .firstName("UFirst")
            .lastName("ULast")
            .middleName("UMiddle")
            .suffix(Suffix.III)
            .phone("1234567890")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateOwnerInfo)
            .when()
            .put()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);

        given()
            .when()
            .get("/200")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("addressId", is(2),
                "firstName", is(updateOwnerInfo.getFirstName()),
                "lastName", is(updateOwnerInfo.getLastName()),
                "middleName", is(updateOwnerInfo.getMiddleName()),
                "suffix", is(updateOwnerInfo.getSuffix().name()),
                "email", is("updatetest@email.com"),
                "phone", is(updateOwnerInfo.getPhone())
            );
    }
}
