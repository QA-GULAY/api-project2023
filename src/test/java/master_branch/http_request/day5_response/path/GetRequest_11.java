package master_branch.http_request.day5_response.path;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest_11 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("regres_url");
    }

    @Test
    public void queryParamTest() {
        //1.yol
        //response a 2 queryparam (key value seklinde ) ile istekte bulunduk

        Response response = given().accept(ContentType.JSON)
                .queryParam("page", "2")
                .queryParam("per_page", "3")
                .get();
        //  response.prettyPrint();
        response.then().assertThat()
                .contentType("application/json")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
        int firstDataId = response.path("data[0].id");
        int firstDataId2 = response.path("data.id[0]");
        System.out.println("firstDataId2 = " + firstDataId2);
        String firstDataEmail = response.path("data[0].email");
        String firstDataFirst_name = response.path("data[0].first_name");
        String firstDataLast_name = response.path("data[0].last_name");
        String firstDataAvatar = response.path("data.avatar[0]");

        assertEquals(4, firstDataId);
        assertEquals("eve.holt@reqres.in", firstDataEmail);
        assertEquals("Eve", firstDataFirst_name);
        assertEquals("Holt", firstDataLast_name);
        assertEquals("https://reqres.in/img/faces/4-image.jpg", firstDataAvatar);
        String nameEve = response.path(("data.find{it.id==4}.first_name"));
        System.out.println("nameEve = " + nameEve);


    }

    @Test
    public void queryParamsTest() {
        //2.yol
        //response a  queryparams (key value seklinde ) ile istekte bulunduk

        Response response = given().accept(ContentType.JSON)
                .queryParams("page", "2","per_page", "3")
                .get();
        //  response.prettyPrint();
        response.then().assertThat()
                .contentType("application/json")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
        int firstDataId = response.path("data[0].id");
        int firstDataId2 = response.path("data.id[0]");
        System.out.println("firstDataId2 = " + firstDataId2);
        String firstDataEmail = response.path("data[0].email");
        String firstDataFirst_name = response.path("data[0].first_name");
        String firstDataLast_name = response.path("data[0].last_name");
        String firstDataAvatar = response.path("data.avatar[0]");

        assertEquals(4, firstDataId);
        assertEquals("eve.holt@reqres.in", firstDataEmail);
        assertEquals("Eve", firstDataFirst_name);
        assertEquals("Holt", firstDataLast_name);
        assertEquals("https://reqres.in/img/faces/4-image.jpg", firstDataAvatar);
        String nameEve = response.path(("data.find{it.id==4}.first_name"));
        System.out.println("nameEve = " + nameEve);

    }
}