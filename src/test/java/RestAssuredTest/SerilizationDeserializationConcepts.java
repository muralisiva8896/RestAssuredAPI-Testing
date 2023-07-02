package RestAssuredTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

//pojo--Serialization will be done internally in RestAssured--JSON---Deserialization--Pojo
public class SerilizationDeserializationConcepts {

    @Test
    public void pojo2Json() throws JsonProcessingException {
        //Created java object using pojo class
        PojoClassStudent data = new PojoClassStudent();//pojo
        data.setName("Murali");
        data.setLocation("India");
        data.setPhone("+916378239493");
        String courseArr[] ={"java", "python", "API Testing"};
        data.setCourses(courseArr);

        //convert java object --> json object(serialization)
        ObjectMapper objmap = new ObjectMapper();//import ObjectMapper from fastxml jackson binder
        String jsonData=objmap.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        System.out.println(jsonData);

    }

    @Test
    public void Json2Pojo() throws JsonProcessingException {

        String jsondata ="{\n" +
                "  \"name\" : \"Murali\",\n" +
                "  \"location\" : \"India\",\n" +
                "  \"phone\" : \"+916378239493\",\n" +
                "  \"courses\" : [ \"java\", \"python\", \"API Testing\" ]\n" +
                "}\n";

        //Convert Json to pojo object (Deserialization)
        ObjectMapper objectMapper = new ObjectMapper();
        PojoClassStudent pojoObject =objectMapper.readValue(jsondata, PojoClassStudent.class); //Convert Json to pojo object specifying pojo class format by PojoClassStudent.class

        //System.out.println(pojoObject);
        System.out.println(pojoObject.getName());    // getting the data using converted pojo object
        System.out.println(pojoObject.getLocation());
        System.out.println(pojoObject.getPhone());
        for(int i=0; i<pojoObject.getCourses().length; i++) {
            int c = i+1;
            System.out.print("Course "+ c +":" + pojoObject.getCourses()[i]+ ", ");
        }
    }
}
