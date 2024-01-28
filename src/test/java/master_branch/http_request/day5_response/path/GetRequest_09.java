package master_branch.http_request.day5_response.path;

import Utulities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest_09 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }
    @Test
    public void test(){
        Response response=given().accept(ContentType.JSON).get("getPerson/561e90f0-97d0-44b2-bbe2-c3d545cde661");
        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json");
        String personId=response.path("personId");
        System.out.println("personId = " + personId);
        String name=response.path("name");
        String age=response.path("age");
        String email=response.path("email");
        String city=response.path("address.city");
        String zipcode=response.path("address.zipcode");
        String country=response.path("address.country");

        assertEquals("561e90f0-97d0-44b2-bbe2-c3d545cde661",personId);
        assertEquals("Okan",name);
        assertEquals("56",age);
        assertEquals("okan@gmail.com",email);
        assertEquals("Artvin",city);
        assertEquals("67300",zipcode);
        assertEquals("Turkiye",country);



    }

}
