package RestAssuredTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class ParsingJSONRespones {


    @Test//
    public void ValidateJsonResponseApproach1(){
        given()
           .contentType(ContentType.JSON)
       .when()
                .get("http://localhost:3000/book")//Run the store.json in CMD using command "json-server store.json" in store.json file location
        .then()
                .statusCode(200)
                .header("Content-Type",equalTo("application/json; charset=utf-8"))
                .body("[2].title",equalTo("Moby Dick"))//Use JSON Findpath to find the JSON Path
                .log().all();
    }
    @Test
    public void ValidateJsonResponseApproach2(){
       Response res = given()
             .contentType(ContentType.JSON)
           .when()
             .get("http://localhost:3000/book");//Run the store.json in CMD using command "json-server store.json" in store.json file location
        Assert.assertEquals(res.getStatusCode(),200);//Validation 1
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");//Validation 2
        String title = res.jsonPath().get("[2].title".toString());//Validation 3
        Assert.assertEquals(title, "Moby Dick");//Validation 4 added to check commit

    }
    @Test
    public void ValidateJsonResponseApproach3(){
        Response res = given()
                         .contentType(ContentType.JSON)
                      .when()
                         .get("http://localhost:3000/book");//Using JSON Object class

        JSONObject json = new JSONObject(res.asString());//converting response to json object type(Response should be converted to string)
         for(int i=0; i< json.getJSONArray("book").length(); i++){
            String bookTitle = json.getJSONArray("book").getJSONObject(i).get("title").toString();//Getting the JSON Array and inside array getting the particular JSON object
            System.out.println("Title of the Book:" +"   "+ bookTitle );
          }
        }

}
