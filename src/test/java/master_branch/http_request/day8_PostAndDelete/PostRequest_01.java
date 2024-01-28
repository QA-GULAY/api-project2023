package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest_01 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }

    @Test
    public void getTest() {
        String requestBody="{\n" +
                "    \"personId\": \"cf403a71-89df-4bde-877b-5737539a3d4f\",\n" +
                "    \"name\": \"Aysel\",\n" +
                "    \"age\": \"40\",\n" +
                "    \"email\": \"aysel_d@gmail.com\",\n" +
                "    \"address\": {\n" +
                "        \"city\": \"Mardin\",\n" +
                "        \"zipcode\": \"65231\",\n" +
                "        \"country\": \"Turkiye\"\n" +
                "    }\n" +
                "}";
        //get metodunda contenType .accept(ContentType.JSON)yaziliyordu post ta ise contenType(.accept(ContentType.JSON))icine yazliyor
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).post("addPerson");
        response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals("Aysel",response.path("name"));
        assertEquals("40",response.path("age"));
        assertEquals("aysel_d@gmail.com",response.path("email"));
        assertEquals("Mardin",response.path("address.city"));
        assertEquals("65231",response.path("address.zipcode"));
        assertEquals("Turkiye",response.path("address.country"));

        //post yaptiktan sonra ekledigimiz kisi silmeliyiz
        Response response1=given().pathParam("id",response.path("personId")).delete("deletePerson/{id}");
        response1.prettyPrint();
        assertEquals(200,response1.statusCode());
        assertEquals("text/plain;charset=UTF-8",response1.contentType());

        assertEquals("Person Deleted Successfully!",response1.asString());
    }
}