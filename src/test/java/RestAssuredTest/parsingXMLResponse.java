package RestAssuredTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class parsingXMLResponse {

    @Test
    public void testXMLresponse(){
        given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")

                .then()
                .statusCode(200)
                .header("Content-Type",equalTo("application/xml; charset=utf-8"))
                .body("TravelerinformationResponse.page",equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("Developer"))
                .log().all();

    }
    @Test
    public void ValidateXMLresponseApproach2() {
        Response res = given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");
        Assert.assertEquals(res.getStatusCode(), 200);//Validation 1
        Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");//Validation 2
        String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNo,"1");
        String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelerName,"Developer");
    }
}
