package RestAssuredTest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequest {

    int getId=0;

    @Test(priority = 1)
    void getUsers(){
        when()
           .get("https://reqres.in/api/users?page=2")
        .then()
           .statusCode(200)
           .body("page", equalTo(2))
           .log().all();
    }

    @Test(priority = 2)
    void createUser(){
        HashMap data = new HashMap();
        data.put("name", "Murali");
        data.put("job", "PA");
        getId=given()
          .contentType("application/json")
          .body(data)
       .when()
          .post("https://reqres.in/api/users")
          .jsonPath().getInt("id");

    }
    @Test(priority = 3)
    void updateUser(){
        HashMap data = new HashMap();
        data.put("name", "Muralidharan");
        data.put("job", "QA Engineer");
        given()
          .contentType("application/json")
          .body(data)
       .when()
          .put("https://reqres.in/api/users/" + getId)
       .then()
          .statusCode(200)
          .log().all();
    }
    @Test(priority = 4)
    void deleteUsers(){
        when()
          .delete("https://reqres.in/api/users/2")
        .then()
          .statusCode(204)
          .log().all();
    }

}
