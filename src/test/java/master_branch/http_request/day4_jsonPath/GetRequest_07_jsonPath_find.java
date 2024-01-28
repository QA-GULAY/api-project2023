package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetRequest_07_jsonPath_find {
    @Before
    public void setUp(){

        baseURI= ConfigurationReader.getProperties("url");
    }
    @Test
    public void getTest() {
        Response response = given().accept(ContentType.JSON).get("getAllPeople");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        JsonPath jsonPath=response.jsonPath();

        //find{} metodu ile spesifik bi veri bulmak istedigimizde

        String enesEmail=jsonPath.getString("find{it.name==\"enes\"}.email");
        String enesEmail2=jsonPath.getString("find{it.personId==\"3862b48d-3445-4865-93a7-6fe64690a8d9\"}.email");
        System.out.println("enesEmail="+enesEmail);
        System.out.println("enesEmail="+enesEmail2);
        Assert.assertEquals("enes@gmail.com",enesEmail);

        //findAll{} ise tum bilgileri list seklinde getirir

        List<String>enesList=jsonPath.getList("findAll{it.name==\"enes\"}");
        System.out.println("enesList="+enesList);

    }
    }