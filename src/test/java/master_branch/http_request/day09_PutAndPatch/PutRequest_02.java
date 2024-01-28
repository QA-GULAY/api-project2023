package master_branch.http_request.day09_PutAndPatch;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.testDatas.TestMasterBranchData;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PutRequest_02 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }

    @Test
    public void putTestByMap() {
        TestMasterBranchData data=new TestMasterBranchData();

        Map<String,Object>requestBody=data.masterBranchData();
        requestBody.put("age","51");
        requestBody.put("email","jhon@gmail.com");

        Response response=given().contentType(ContentType.JSON).and().body(requestBody).when().put("updatePerson");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("text/plain;charset=UTF-8",response.contentType());
        assertEquals("record updated successfully.",response.asString());

        Response responseGet=given().accept(ContentType.JSON).pathParam("id",requestBody.get("personId")).get("getPerson/{id}");
        assertEquals(200,responseGet.statusCode());
        assertEquals("application/json",responseGet.contentType());
        assertEquals(requestBody.get("age"),responseGet.path("age"));
        assertEquals(requestBody.get("email"),responseGet.path("email"));


    }
}