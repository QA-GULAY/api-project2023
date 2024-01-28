package master_branch.http_request.day09_PutAndPatch;

import Utulities.ConfigurationReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Etut_Patch {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("json_url");
    }
    @Test
    public void patchTest() throws JsonProcessingException {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("name", "master");
        expectedData.put("username", "branch");
        expectedData.put("email", "masterbranch@mail.com");
        Response response=given().contentType(ContentType.JSON).and().body(expectedData).pathParam("id",1).when().patch("users/{id}");
        response.prettyPrint();

        expectedData.put("id",1);
        assertEquals(200,response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        Map<String, Object>actualResponse=response.as(Map.class);

        assertEquals(expectedData.get("name"),actualResponse.get("name"));
        assertEquals(expectedData.get("username"),actualResponse.get("username"));
        assertEquals(expectedData.get("email"),actualResponse.get("email"));
        assertEquals(expectedData.get("id"),actualResponse.get("id"));

        //classlar arasi convert(degisimi) objectmapper ile de yapabiliriz  ,*response file ise input stream ile convert olur
        //cevap 2.yol mapper ile
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String,Object> actualBody = mapper.readValue(response.asString(), HashMap.class);
        expectedData.put("id",1);
        Assert.assertEquals(expectedData.get("name"),actualBody.get("name"));
        Assert.assertEquals(expectedData.get("id"),actualBody.get("id"));
        Assert.assertEquals(expectedData.get("username"),actualBody.get("username"));
        Assert.assertEquals(expectedData.get("email"),actualBody.get("email"));
        System.out.println("actualBody = " + actualBody);
        System.out.println("expectedData = " + expectedData);
    }

    }
