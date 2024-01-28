package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class etut2 {
    /*1. GET methodu ile https://reqres.in/api/users/11 urline istekte bulunun
            2. headers lardan
    Content-Type = application/json; charset=utf-8
    statusCode = 200
            3. gelen response danki kisinin asagidaki gibi oldugunu dogrulayin
       "data": {
        "id": 11,
                "email": "george.edwards@reqres.in",
                "first_name": "George",
                "last_name": "Edwards",
                "avatar": "https://reqres.in/img/faces/11-image.jpg"*/
    @Before
    public void setUp() {

        RestAssured.baseURI = ConfigurationReader.getProperties("regres_url");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given().accept(ContentType.JSON).get("/11 ");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(11,jsonPath.getInt("data.id"));
        Assert.assertEquals("george.edwards@reqres.in",jsonPath.getString("data.email"));
        Assert.assertEquals("George",jsonPath.getString("data.first_name"));
        Assert.assertEquals("Edwards",jsonPath.getString("data.last_name"));
        Assert.assertEquals("https://reqres.in/img/faces/11-image.jpg",jsonPath.getString("data.avatar"));

    }
}