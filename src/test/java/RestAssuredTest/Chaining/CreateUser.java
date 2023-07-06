package RestAssuredTest.Chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateUser{
    @Test
    public void test_createUser(ITestContext context){
    Faker faker = new Faker();
    JSONObject data = new JSONObject();
    String bearerToken = "2d060e66d3c82e26bf94a516ccb0d10c5db9fbfef86573b9d789d06021ac1781"; // generated from gorest.api.co.in using github login

    data.put("name",faker.name().fullName());
    data.put("gender", "Male");
    data.put("email", faker.internet().emailAddress());
    data.put("status","Inactive");

    int id =given()
            .header("Authorization","Bearer "+bearerToken)
            .contentType("application/json")
            .body(data.toString())
            .when()
            .post("https://gorest.co.in/public/v2/users")
            .jsonPath().getInt("id");

    System.out.println(id);
//    context.setAttribute("user_id", id);// used to set attribute test level
     context.getSuite().setAttribute("user_id", id); // used to set attribute at suite level

    }
}
