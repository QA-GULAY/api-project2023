package master_branch.http_request.day7_Map;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.testDatas.TestMasterBranchData;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest_JsonObject {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("regres_url");
    }

    @Test
    public void jsonObject() {
        Response response = given().accept(ContentType.JSON).pathParam("regId", 12).get("/{regId}");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");

        TestMasterBranchData data = new TestMasterBranchData();

        JSONObject expectedJson=data.getRegresDataByJyson();
        //map te =response.as(Map.class) ile almistik verileri jsonObject ile bu sekil(new JSONObject(response.asString())
        JSONObject actualJson=new JSONObject(response.asString());

        System.out.println("actualJson = " + actualJson);

        assertEquals(((JSONObject)expectedJson.get("data")).get("id"),(((JSONObject)actualJson.get("data")).get("id")));
        assertEquals(((JSONObject)expectedJson.get("data")).get("email"),(((JSONObject)actualJson.get("data")).get("email")));
        assertEquals(((JSONObject)expectedJson.get("data")).get("first_name"),(((JSONObject)actualJson.get("data")).get("first_name")));
        assertEquals(((JSONObject)expectedJson.get("data")).get("last_name"),(((JSONObject)actualJson.get("data")).get("last_name")));
        assertEquals(((JSONObject)expectedJson.get("data")).get("avatar"),(((JSONObject)actualJson.get("data")).get("avatar")));

        assertEquals(((JSONObject)expectedJson.get("support")).get("url"),(((JSONObject)actualJson.get("support")).get("url")));
        assertEquals(((JSONObject)expectedJson.get("support")).get("text"),(((JSONObject)actualJson.get("support")).get("text")));




    }
}
