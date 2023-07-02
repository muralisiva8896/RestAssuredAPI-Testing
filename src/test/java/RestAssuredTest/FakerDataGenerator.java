package RestAssuredTest;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {
    @Test
    public void generateDummyTestDataUsingFaker(){
        Faker faker = new Faker();
        String fullName=faker.name().fullName();
        String address = faker.address().fullAddress();
        String phone=faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();
        String userName=faker.name().username();
        String password=faker.internet().password(6,15,true,true,true);

        System.out.println("Name: "+fullName+", Address: "+address+", Mobile: "+phone+", Email: "+email+", Username: "+userName+", Password: "+password);
    }
}
