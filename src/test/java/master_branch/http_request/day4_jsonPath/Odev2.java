package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class Odev2 {
    /* 1. GET methodu ile https://hp-api.onrender.com/api/spells urline istekte bulunun
       2. headers lardan
           Content-Type = application/json; charset=utf-8
           statusCode = 200

       3. gelen response hepsini bir List<Map<String,String>> e alin
          a. adetini yazdirin
       4. gelen response dan id: "43d3d53e-7ab9-4145-bda7-d96be99c5d31" olan kisinin asagidaki gibi oldugunu dogrulayin

              id "43d3d53e-7ab9-4145-bda7-d96be99c5d31",
              name: "Silencio",
              description: "Silences the target"   */
    @Before
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperties("onrender_url");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given().accept(ContentType.JSON).get("spells");
        //  response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());
        JsonPath json = response.jsonPath();

        List<Map<String, String>> list = json.getList("findAll{it}");
        System.out.println("list.size() = " + list.size());
        System.out.println("list = " + list);
        Map<String,String>myResponse=json.getMap("find{it.id=='43d3d53e-7ab9-4145-bda7-d96be99c5d31' }");
        Assert.assertEquals("43d3d53e-7ab9-4145-bda7-d96be99c5d31", myResponse.get("id"));
        Assert.assertEquals("Silencio", myResponse.get("name"));
        Assert.assertEquals("Silences the target", myResponse.get("description"));
    }
}




