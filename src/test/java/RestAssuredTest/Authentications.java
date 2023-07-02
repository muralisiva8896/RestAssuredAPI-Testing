package RestAssuredTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Authentications {
    @Test(priority = 0)
    public void BasicAuthentication(){
        given()
                .auth().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority = 1)
    public void DigestAuthentication(){
        given()
                .auth().digest("postman","password")//Difference between basic and digest is internal process in API will be different
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority = 2)
    public void PreemptiveAuthentication(){
        given()
                .auth().preemptive().basic("postman","password")//Difference between basic, digest and preemptive is combination of digest and basic -internal process in API will be different
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority = 3)
    public void testBearerTokenAuthentication(){

        String bearerToken = "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"; //generated from github in developer settings for personalised accces to repositories in my github
        given()
                .header("Authorization","Bearer "+bearerToken) //Bearer is the keyword added before token, Authorization is key
                .when()
                .get("https://api.github.com/users/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void testOAuth1Authentication(){
        given()
                .auth().oauth("consumerKey","consumerSecrat","accessToken","tokenSecrate")//OAuth 1 Syntax
                .when()
                .get("url")
                .then()
                .statusCode(200);
    }

    @Test(priority = 5)
    public void testOAuth2Authentication(){
        given()
                .auth().oauth2("accessToken")//OAuth 2 Syntax, only pass access token
                .when()
                .get("url")
                .then()
                .statusCode(200);
    }
    @Test(priority = 6)
    public void testAPIKeyAuthentication() {
    given()
            .queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c") //appid is key
            .pathParam("mypath", "data/2.5/forecast/daily")
            .queryParam("q","Delhi")
            .queryParam("units","metric")
            .queryParam("cnt","7")

            .when()
            //.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7") //Method 1 passing directly the entire url in get
            .get("https://api.openweathermap.org/{mypath}")//Method 2 passing only domain in get, path and query parameter is declared in given method
            .then()
            .statusCode(200)
            .log().all();
    }
}
