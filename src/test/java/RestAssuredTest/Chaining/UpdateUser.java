package RestAssuredTest.Chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UpdateUser {
    @Test
    public void test_updateUser(ITestContext context){
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        String bearerToken = "2d060e66d3c82e26bf94a516ccb0d10c5db9fbfef86573b9d789d06021ac1781";

        data.put("name",faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status","Inactive");
        int id = (int) context.getAttribute("user_id");//this should come from create user


        given()
                .header("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .pathParam("id",id)
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();

    }
}
