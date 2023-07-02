package RestAssuredTest;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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
    @Test
    public void ValidateXMLresponseApproach3() {
        Response res = given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");
        XmlPath xmlobj = new XmlPath(res.asString());//converting entire response to string so we use asString menthod

        List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");//getting all the travelers in List
        System.out.println(travellers.size());
        Assert.assertEquals(travellers.size(),10);

        List<String> travellers_name = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");//using xmlpath to get all the travellers name in List
        for(String name:travellers_name)
        {
            System.out.println(name);
        }

    }
}
