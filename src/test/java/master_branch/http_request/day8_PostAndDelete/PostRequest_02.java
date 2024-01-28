package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.http_request.pojos.regres.pojo.Address;
import master_branch.http_request.pojos.regres.pojo.Master_branchPojo;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostRequest_02 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }

    @Test  //1.YOL CONSTRUCTOR KULLANARAK
    public void postTestByPojo() {
        Address address = new Address("Izmir","52413","Turkiye");
        Master_branchPojo requestBody = new Master_branchPojo("10001","Mehmet","50","msen@gmail.com",address);

        Response response = given().contentType(ContentType.JSON).and().body(requestBody).post("addPerson");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        Master_branchPojo actualResponse=response.as(Master_branchPojo.class);

        assertEquals(requestBody.getName(),actualResponse.getName());
        assertEquals(requestBody.getAge(),actualResponse.getAge());
        assertEquals(requestBody.getEmail(),actualResponse.getEmail());
        assertEquals(requestBody.getAddress().getCity(),actualResponse.getAddress().getCity());
        assertEquals(requestBody.getAddress().getZipcode(),actualResponse.getAddress().getZipcode());
        assertEquals(requestBody.getAddress().getCountry(),actualResponse.getAddress().getCountry());

        Response response1=given().pathParam("id",response.path("personId")).delete("deletePerson/{id}");
        response1.prettyPrint();
        assertEquals(200,response1.statusCode());
        assertEquals("text/plain;charset=UTF-8",response1.contentType());

        assertEquals("Person Deleted Successfully!",response1.asString());
    }
    @Test  //2.YOL CONSTRUCTOR KULLANMADAN ,verileri set metodu ile ekleyerek
    public void postTestByPojo2() {
        Address address = new Address();
        address.setCity("lisboa");
        address.setZipcode("25136");
        address.setCountry("Portugal");
        Master_branchPojo requestBody = new Master_branchPojo();
        requestBody.setName("Maria");
        requestBody.setAge("17");
        requestBody.setEmail("maria@gmail.com");
        requestBody.setPersonId("10002");
        requestBody.setAddress(address);


        Response response = given().contentType(ContentType.JSON).and().body(requestBody).post("addPerson");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        Master_branchPojo actualResponse=response.as(Master_branchPojo.class);

        assertEquals(requestBody.getPersonId(),actualResponse.getPersonId());
        assertEquals(requestBody.getName(),actualResponse.getName());
        assertEquals(requestBody.getAge(),actualResponse.getAge());
        assertEquals(requestBody.getEmail(),actualResponse.getEmail());
        assertEquals(requestBody.getAddress().getCity(),actualResponse.getAddress().getCity());
        assertEquals(requestBody.getAddress().getZipcode(),actualResponse.getAddress().getZipcode());
        assertEquals(requestBody.getAddress().getCountry(),actualResponse.getAddress().getCountry());

        Response response1=given().pathParam("id",response.path("personId")).delete("deletePerson/{id}");
        response1.prettyPrint();
        assertEquals(200,response1.statusCode());
        assertEquals("text/plain;charset=UTF-8",response1.contentType());

        assertEquals("Person Deleted Successfully!",response1.asString());
    }
}