package RestAssuredTest;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HeaderValidation {

    @Test(priority = 0)
    public void validatingHeader(){
        given()

        .when()
           .get("https://www.google.com/")

        .then()
           .header("Content-Type", "text/html; charset=ISO-8859-1")
           .header("Server", "gws")
           .log().headers();
    }

    @Test(priority = 1)
    public void getHeader() {
        Response res = given()

                .when()
                .get("https://www.google.com/");
        Headers myheaders = res.getHeaders();
        for (Header h : myheaders) {
            System.out.println(h.getName() + "     " + h.getValue());
        }

    }
}
