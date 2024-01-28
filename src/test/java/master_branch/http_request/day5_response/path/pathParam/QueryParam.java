package master_branch.http_request.day5_response.path.pathParam;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class QueryParam {
    /* 1. GET methodu ile https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe urline queryParam kullanarak istekte bulunun
             2. headers lardan
     Content-Type = application/json; charset=utf-8
     statusCode = 200
             3. gelen response daki bookingid leri List<Integer> e alin
     a. adetini yazdirin
     b. 2012,3897,14 ve bu rakamlarin listin icinde oldugunu dogrulayin*/
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("restful_url");
    }

    @Test
    public void SingleQueryParamTest() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("firstname", "Jane","lastname","Doe")
                .get();
          response.prettyPrint();
        response.then().assertThat()
                .contentType("application/json; charset=utf-8")
                .statusCode(200);

        List<Integer>bookingId=response.path("findAll{it}.bookingid");
        System.out.println("bookingId = " + bookingId);
        System.out.println("bookingId.size() = " + bookingId.size());
        


    }
}
