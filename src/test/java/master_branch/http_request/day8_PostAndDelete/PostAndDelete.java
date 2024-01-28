package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostAndDelete {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("pet_store_url");
    }

    @Test
    public void postTestByMap() {
        Map<String, Object> expectedPost = new HashMap<>();
        expectedPost.put("id", 2200);
        expectedPost.put("petId", 15);
        expectedPost.put("quantity",520);
        expectedPost.put("shipDate", "2024-11-16T19:51:29.127+0000");
        expectedPost.put("status","available");
        expectedPost.put( "complete", true);

        Response response = given().contentType(ContentType.JSON).and().body(expectedPost).post("store/order");
        response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertEquals(expectedPost.get("id"),response.path("id"));
        assertEquals(expectedPost.get("petId"),response.path("petId"));
        assertEquals(expectedPost.get("quantity"),response.path("quantity"));
        assertEquals(expectedPost.get("shipDate"),response.path("shipDate"));
        assertEquals(expectedPost.get("status"),response.path("status"));
        assertEquals(expectedPost.get("complete"),response.path("complete"));

        //delete yaptiktan sonra gelen mesaji map a aticaz
        Map<String, Object> deleteMessage = new HashMap<>();
        deleteMessage.put("code", 200);
        deleteMessage.put("type", "unknown");
        deleteMessage.put("message", "2200");

        Response responseDelete=given().delete("store/order/"+response.path("id"));
        responseDelete.prettyPrint();
        Map<String, Object>actualMessage=responseDelete.as(Map.class);

        assertEquals(deleteMessage.get("code"),actualMessage.get("code"));
        assertEquals(deleteMessage.get("type"),actualMessage.get("type"));
        assertEquals(deleteMessage.get("message"),actualMessage.get("message"));
    }
}