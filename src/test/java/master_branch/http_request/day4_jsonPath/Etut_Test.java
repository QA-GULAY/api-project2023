package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class Etut_Test {
    /*GET methodu ile https://reqres.in/api/users?page=2 urline istekte bulunun
            2. headers lardan
    Content-Type = application/json; charset=utf-8
    statusCode = 200
            3. gelen response dan last_name="Fields" olan kisinin asagidaki gibi oldugunu dogrulayin
       "data":{
        "id": 10,
                "email": "byron.fields@reqres.in",
                "first_name": "Byron",
                "last_name": "Fields",
                "avatar": "https://reqres.in/img/faces/10-image.jpg"*/

    @Before
    public void setUp(){

        RestAssured.baseURI= ConfigurationReader.getProperties("regres_url");
    }
    @Test
    public void getTest() {
        Response response = RestAssured.given().accept(ContentType.JSON).get("?page=2 ");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        JsonPath json=response.jsonPath();

        Map<String,String>  mapResponse = json.getMap("data.find{it.last_name=='Fields'}");
        System.out.println("mapResponse = " + mapResponse);

        Assert.assertEquals(10,mapResponse.get("id"));
        Assert.assertEquals("byron.fields@reqres.in",mapResponse.get("email"));
        Assert.assertEquals("Byron",mapResponse.get("first_name"));
        Assert.assertEquals("Fields",mapResponse.get("last_name"));
        Assert.assertEquals("https://reqres.in/img/faces/10-image.jpg",mapResponse.get("avatar"));
    }
}
