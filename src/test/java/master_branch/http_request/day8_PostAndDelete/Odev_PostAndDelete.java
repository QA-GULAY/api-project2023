package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.testDatas.TestMasterBranchData;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Odev_PostAndDelete {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("restapi_url");
    }

    @Test
    public void postTestByPojo() {
        TestMasterBranchData dummyData = new TestMasterBranchData();

        Map<String,Object> requestBody = dummyData.createTest();

        Response response = given().contentType(ContentType.JSON).and().body(requestBody).post("create ");
        response.prettyPrint();
        Map<String,Object> expectedMap=dummyData.createExpexdetData();

        Map<String,Object> actualMap=response.as(Map.class);
        System.out.println("actualMap.toString() = " + actualMap.toString());
        assertEquals(expectedMap.get("status"),actualMap.get("status"));
        assertEquals(expectedMap.get("message"),actualMap.get("message"));

        assertEquals(((HashMap)expectedMap.get("data")).get("name"),((HashMap)actualMap.get("data")).get("name"));
        assertEquals(((HashMap)expectedMap.get("data")).get("salary"),((HashMap)actualMap.get("data")).get("salary"));
        assertEquals(((HashMap)expectedMap.get("data")).get("age"),((HashMap)actualMap.get("data")).get("age"));

        Response responseDelete=given().delete("delete/:id/"+response.path("id"));
        responseDelete.prettyPrint();
        Map<String, Object>actualMessage=responseDelete.as(Map.class);

        Map<String, Object> deleteMessage = new HashMap<>();
        deleteMessage.put("status", "success");
        deleteMessage.put("data", "id");
        deleteMessage.put("message", "Successfully! Record has been deleted");

        assertEquals(deleteMessage.get("status"),actualMessage.get("status"));
        assertEquals(deleteMessage.get("data"),actualMessage.get("data"));
        assertEquals(deleteMessage.get("message"),actualMessage.get("message"));
    }

}
