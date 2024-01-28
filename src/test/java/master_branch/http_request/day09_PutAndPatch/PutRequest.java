package master_branch.http_request.day09_PutAndPatch;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.PersonEPMethods;
import master_branch.http_request.pojos.regres.pojo.Address;
import master_branch.http_request.pojos.regres.pojo.Master_branchPojo;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PutRequest {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }
    PersonEPMethods epMethods=new PersonEPMethods();
    @Test
    public void putRequestByPojo() {
        Address address = new Address();
        address.setCity("Erzurum");
        address.setZipcode("569812");
        address.setCountry("Turkiye");

        Master_branchPojo requestBody = new Master_branchPojo();
        requestBody.setName("SEREF");
        requestBody.setAge("51");
        requestBody.setEmail("SEREF@gmail.com");
        requestBody.setPersonId("111000");
        requestBody.setAddress(address);

        Response response=given().contentType(ContentType.JSON).and().body(requestBody).when().put("updatePerson");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("text/plain;charset=UTF-8",response.contentType());
        assertEquals("record updated successfully.",response.asString());








    }
}