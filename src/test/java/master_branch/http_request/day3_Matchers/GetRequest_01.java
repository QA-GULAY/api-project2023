package master_branch.http_request.day3_Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class GetRequest_01 {
    @Test
    public void getTest(){
        String url="https://masterbranch-9f81b37abe98.herokuapp.com/api/v1/getAllPeople";
        Response response= RestAssured.given().get(url);

        //response.prettyPrint();

        System.out.println("Status Code="+response.statusCode());
        System.out.println("Content Type="+response.contentType());
        System.out.println("Date="+response.header("Date"));

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json",response.contentType());
        Assert.assertEquals("1.1 vegur",response.header("Via"));

    }
}
