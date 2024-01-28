package master_branch.http_request.day09_PutAndPatch;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Etut_putMetod {
    @Before
    public void setUp() {
       /* 1.  Put methodu ile https://jsonplaceholder.typicode.com/todos/200 pathParam kullanarak asagidaki body ile istekde bulunun
        {
            "userId": 10,
                "title": "master branch",
                "completed": true
        }*/

        baseURI = ConfigurationReader.getProperties("json_url");
    }

    @Test
    public void putTest() {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 10);
        expectedData.put("title", "master branch");
        expectedData.put("completed", true);
        Response response=given().contentType(ContentType.JSON).and().body(expectedData).pathParam("id",200).when().put("todos/{id}");
        response.prettyPrint();

        expectedData.put("id",200);
        assertEquals(200,response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        assertEquals(expectedData.get("userId"),response.path("userId"));
        assertEquals(expectedData.get("completed"),response.path("completed"));
        assertEquals(expectedData.get("title"),response.path("title"));
        assertEquals(expectedData.get("id"),response.path("id"));

        //2.yol assert gelen response u map a convert ediyoruz as()metodu ile
        Map<String, Object>actualResponse=response.as(Map.class);

        assertEquals(expectedData.get("userId"),actualResponse.get("userId"));
        assertEquals(expectedData.get("completed"),actualResponse.get("completed"));
        assertEquals(expectedData.get("title"),actualResponse.get("title"));
        assertEquals(expectedData.get("id"),actualResponse.get("id"));

    }
}