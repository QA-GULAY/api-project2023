package master_branch.http_request.day6_De_serialization;

import Utulities.ConfigurationReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.http_request.pojos.regres.pojo.master_branch1.RegresPojo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetPojoTest_15 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("regres_url");
    }
    @Test
    public void pojoTest() {
        Response response=given().accept(ContentType.JSON).pathParams("pathId2",12).get("/{pathId2}");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json");

        RegresPojo regresPojo=response.as(RegresPojo.class);
        System.out.println("regresPojo = " + regresPojo);

        assertEquals(12,(Object)regresPojo.getData().getId());
        assertEquals("rachel.howell@reqres.in",regresPojo.getData().getEmail());
        assertEquals("Rachel",regresPojo.getData().getFirstName());
        assertEquals("Howell",regresPojo.getData().getLastName());
        assertEquals("https://reqres.in/img/faces/12-image.jpg",regresPojo.getData().getAvatar());

        assertEquals("https://reqres.in/#support-heading",regresPojo.getSupport().getUrl());
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",regresPojo.getSupport().getText());


    }
    @Test
    public void pojoTest2() throws JsonProcessingException {
        Response response=given().accept(ContentType.JSON).pathParams("pathId2",12).get("/{pathId2}");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json");
        //1.yol
       // RegresPojo regresPojo=response.as(RegresPojo.class);

        //2.yol
        ObjectMapper mapper=new ObjectMapper();
        RegresPojo regresPojo=mapper.readValue(response.asString(),RegresPojo.class);

        System.out.println("regresPojo = " + regresPojo);

        assertEquals(12,(Object)regresPojo.getData().getId());
        assertEquals("rachel.howell@reqres.in",regresPojo.getData().getEmail());
        assertEquals("Rachel",regresPojo.getData().getFirstName());
        assertEquals("Howell",regresPojo.getData().getLastName());
        assertEquals("https://reqres.in/img/faces/12-image.jpg",regresPojo.getData().getAvatar());

        assertEquals("https://reqres.in/#support-heading",regresPojo.getSupport().getUrl());
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",regresPojo.getSupport().getText());


    }
}