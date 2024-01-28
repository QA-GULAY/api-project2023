package master_branch.http_request.day5_response.path.pathParam;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Odev {
   /* 1. GET methodu ile https://reqres.in/api/users?page=2 urline istekte bulunun
        2. headers lardan
    Content-Type = application/json; charset=utf-8
    statusCode = 200
            3. gelen response dan id : 11 olan kisinin asagidaki gibi oldugunu dogrulayin
       "data":{
        "id": 11,
                "email": "george.edwards@reqres.in",
                "first_name": "George",
                "last_name": "Edwards",
                "avatar": "https://reqres.in/img/faces/11-image.jpg"*/
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("regres_url");
    }
    @Test
    public void SingleQueryParamTest() {
        Response response = given().accept(ContentType.JSON)
                            .queryParam("page", 2).get();
       // response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        assertEquals(11,(int)response.path("data[4].id"));
        assertEquals("george.edwards@reqres.in",response.path("data[4].email"));
        assertEquals("George",response.path("data[4].first_name"));
        assertEquals("Edwards",response.path("data[4].last_name"));
        assertEquals("https://reqres.in/img/faces/11-image.jpg",response.path("data[4].avatar"));





    }
}













