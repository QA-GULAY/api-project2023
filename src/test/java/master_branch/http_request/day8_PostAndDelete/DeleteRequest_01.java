package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class DeleteRequest_01 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }

    @Test
    public void deleteTest() {   //pathParam verdigimiz datayi delete icinde suslu parantez ile belirtmeliyiz
        Response response=given().pathParam("id","cf403a71-89df-4bde-877b-5737539a3d4f").delete("deletePerson/{id}");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("text/plain;charset=UTF-8",response.contentType());

        assertEquals("Person Deleted Successfully!",response.asString());

    }
}