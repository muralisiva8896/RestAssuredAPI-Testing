package RestAssuredTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class PathAndQueryParameterPlusCookies {

    @Test(priority = 1)
    public void testPathAndQueryParameters(){

        //https://reqres.in/api/users?page=2&id=5

        given()
                .pathParam("mypath","users")//path parameter
                .queryParam("page",1)// Query Parameters will along with the request automatically
                .queryParam("id",3)// Query Parameters will along with the request automatically
       .when()
                .get("https://reqres.in/api/{mypath}")
       .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 2)
    public void testCookies(){

        given()

        .when()
                .get("https://www.google.com/")
        .then()
                .cookie("AEC","AUEFqZeCgczzYuAvo7NfjdaUpomk9StHzm87tc5frrw8qo2qtJKS7loT0bI")//AEC is cookie name got from postman for Google and values of cookie will reload for every request so this validation will fail always
                .log().all();
    }

    @Test(priority = 3)
    public void testAllCookies(){

        Response res =given()

        .when()
            .get("https://www.google.com/");
//            String cookie_value = res.getCookie("AEC");//printing only single cookie value
//            System.out.println("Cookie Value----->" + cookie_value);//printing only single cookie value

               Map<String, String> cookies_value = res.getCookies();//Java Code so termination is necessary

               for(String k : cookies_value.keySet()){
                   String cookie_value = res.getCookie(k);
                   System.out.println(k+"  "+cookie_value);//Printing all cookies
               }
    }
}

