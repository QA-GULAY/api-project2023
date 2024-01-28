package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.response.Response;
import master_branch.PersonEPMethods;
import master_branch.testDatas.TestMasterBranchData;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class postRequest_03 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }
    PersonEPMethods epMethods=new PersonEPMethods();
    @Test
    public void postTestByMap() {
        TestMasterBranchData masterBranchData = new TestMasterBranchData();

        Map<String,Object> requestBody = masterBranchData.masterBranchData();

        Response response =epMethods.addNewPerson(requestBody);
        response.prettyPrint();

        Map<String,Object> actualMap=response.as(Map.class);

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertEquals(requestBody.get("personId"),actualMap.get("personId"));
        assertEquals(requestBody.get("name"),actualMap.get("name"));
        assertEquals(requestBody.get("age"),actualMap.get("age"));
        assertEquals(requestBody.get("email"),actualMap.get("email"));
        //map icinden map cagirmak icin casting yapiyoruz hashMap ile
        assertEquals(((HashMap)requestBody.get("address")).get("city"),((HashMap)actualMap.get("address")).get("city"));
        assertEquals(((HashMap)requestBody.get("address")).get("zipcode"),((HashMap)actualMap.get("address")).get("zipcode"));
        assertEquals(((HashMap)requestBody.get("address")).get("country"),((HashMap)actualMap.get("address")).get("country"));


        Response response1=given().pathParam("id",response.path("personId")).delete("deletePerson/{id}");
        response1.prettyPrint();
        assertEquals(200,response1.statusCode());
        assertEquals("text/plain;charset=UTF-8",response1.contentType());

        assertEquals("Person Deleted Successfully!",response1.asString());

    }
}
