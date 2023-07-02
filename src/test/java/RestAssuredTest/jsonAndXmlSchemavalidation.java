package RestAssuredTest;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class jsonAndXmlSchemavalidation {

    @Test
    public void ValidateJsonSchema() {

        given()

                .when()
                .get("http://localhost:3000/book")
                .then()
                 .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
    }

    @Test
    public void ValidateXMLSchema(){
        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
    }


}
