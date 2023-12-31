package RestAssuredTest.Chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteUser {
    @Test
    public void test_deleteUser(ITestContext context){
        String bearerToken = "2d060e66d3c82e26bf94a516ccb0d10c5db9fbfef86573b9d789d06021ac1781";
        int id = (int) context.getAttribute("user_id");//this should come from create user
        given()
                .header("Authorization","Bearer "+bearerToken)
                .pathParam("id",id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();

    }
}
