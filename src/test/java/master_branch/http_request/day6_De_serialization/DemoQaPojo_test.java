package master_branch.http_request.day6_De_serialization;
import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.http_request.pojos.DemoQaPojo.DemoQaPojo_datas;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class DemoQaPojo_test {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("bookStore_url");
    }
    @Test
    public void responsePath3(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("ISBN", "9781491904244")
                .get("Book");
        assertEquals(200,response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        response.prettyPrint();
        //gelen response u map a convert ediyoruz as()metodu ile
        DemoQaPojo_datas actualBook=response.as(DemoQaPojo_datas.class);
        DemoQaPojo_datas expectedBook=new DemoQaPojo_datas("9781491904244","You Don't Know JS","ES6 & Beyond","Kyle Simpson","2015-12-27T00:00:00.000Z",
                "O'Reilly Media",278, "No matter how much experience you have with JavaScript, odds are you don’t fully understand the language. As part of the \\\"You Don’t Know JS\\\" series, this compact guide focuses on new features available in ECMAScript 6 (ES6), the latest version of the st"
                ,"https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond" );

        assertEquals(expectedBook.getIsbn(),actualBook.getIsbn());
        assertEquals(expectedBook.getTitle(),actualBook.getTitle());
        assertEquals(expectedBook.getSubTitle(),actualBook.getSubTitle());
        assertEquals(expectedBook.getAuthor(),actualBook.getAuthor());
        assertEquals(expectedBook.getPublish_date(),actualBook.getPublish_date());
        assertEquals(expectedBook.getPublisher(),actualBook.getPublisher());
        assertEquals((int)expectedBook.getPages(),(int)actualBook.getPages());
        assertEquals( expectedBook.getDescription(),actualBook.getDescription());
        assertEquals(expectedBook.getWebsite(),actualBook.getWebsite());
    }
}



