package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetRequest_05_config {
    @Before
    public void setUp(){

        RestAssured.baseURI= ConfigurationReader.getProperties("url");
    }
    @Test
    public void getTest(){
        Response response=RestAssured.given().accept(ContentType.JSON).get("getAllPeople");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json",response.contentType());
        Assert.assertEquals("HTTP/1.1 200",response.statusLine());

        JsonPath jsonPath=response.jsonPath();
        System.out.println("isimler=" + jsonPath.getList("name"));
        System.out.println("Yaslar=" + jsonPath.getList("age"));
        System.out.println("Birinci kisi adi=" + jsonPath.getString("name[0]"));
    }
    @Test
    public void getTest2(){
        Response response=RestAssured.given().accept(ContentType.JSON).get("getPerson/47a303e3-b674-4dd8-91e6-8f305f76e90a");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        JsonPath jsonPath=response.jsonPath();

       Assert.assertEquals("47a303e3-b674-4dd8-91e6-8f305f76e90a",jsonPath.getString("personId"));
       Assert.assertEquals("Yunus",jsonPath.getString("name"));
       Assert.assertEquals("22",jsonPath.getString("age"));
       Assert.assertEquals("yeg@gmail.com",jsonPath.getString("email"));
       Assert.assertEquals("Zonguldak",jsonPath.getString("address.city"));
       Assert.assertEquals("67300",jsonPath.getString("address.zipcode"));
       Assert.assertEquals("Turkiye",jsonPath.getString("address.country"));


    }
}
