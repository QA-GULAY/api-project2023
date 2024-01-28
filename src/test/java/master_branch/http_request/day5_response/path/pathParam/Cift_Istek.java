package master_branch.http_request.day5_response.path.pathParam;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Cift_Istek {

    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("bookStore_url");
    }

    @Test
    public void pathParamTest() {
        Response response = given().accept(ContentType.JSON).get("Books");
        //  response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

        String isbnBesinciKitap = response.path("books[4].isbn");
        assertEquals("9781491904244", isbnBesinciKitap);
        System.out.println("isbnBesinciKitap = " + isbnBesinciKitap);

        //ikinci kez istekte bulunuyoruz birincinin sonucu ile
        // 35.satirda sonucu tirnak icine almadan reponse olarak yazdik

        Response responseBesinciKitap = given().accept(ContentType.JSON)
                .queryParam("ISBN", isbnBesinciKitap).get("Book");
        responseBesinciKitap.prettyPrint();
        assertEquals(200, responseBesinciKitap.statusCode());
        assertEquals("application/json; charset=utf-8", responseBesinciKitap.contentType());
        assertEquals("9781491904244", responseBesinciKitap.path("isbn"));
        assertEquals( "You Don't Know JS", responseBesinciKitap.path("title"));
        assertEquals("ES6 & Beyond",responseBesinciKitap.path("subTitle"));
        assertEquals("Kyle Simpson",responseBesinciKitap.path("author"));
        assertEquals("2015-12-27T00:00:00.000Z",responseBesinciKitap.path("publish_date"));
        assertEquals("https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond", responseBesinciKitap.path("website"));
    }


}



