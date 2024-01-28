package master_branch.http_request.day5_response.path;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest_12 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("pet_store_url");
    }

    @Test
    public void SingleQueryParamTest() {

        //TEK queryparam ile istekte bulunma

        Response response=given().accept(ContentType.JSON)
                .queryParam("status","sold")
                .get("pet/findByStatus");
      //  response.prettyPrint();
        response.then().assertThat()
                .contentType("application/json")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
        String firstCategoryName=response.path("category.name[0]");
        System.out.println("firstCategoryName = " + firstCategoryName);
        Assert.assertEquals("Cat",firstCategoryName);
    }
}