package RestAssuredTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class ParsingJSONRespones {


    @Test//
    public void ValidateJsonResponse(){
        given()
           .contentType("ContentType.JSON")
       .when()
                .get("http://localhost:3000/book")//Run the store.json in CMD using command "json-server store.json" in store.json file location
        .then()
                .statusCode(200)
                .header("Content-Type",equalTo("application/json; charset=utf-8"))
                .body("[2].title",equalTo("Moby Dick"))//Use JSON Findpath to find the JSON Path
                .log().all();
    }


}
