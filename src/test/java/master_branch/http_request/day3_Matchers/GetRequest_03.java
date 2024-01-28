package master_branch.http_request.day3_Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class GetRequest_03 {
    @Test
    public void getTest3(){
        String url="https://masterbranch-9f81b37abe98.herokuapp.com/api/v1/getAllPeople";
        Response response= RestAssured.given().get(url);

        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        response.then().body(
                        "name", Matchers.hasItem("Erkin"),
                "age",Matchers.hasItems("32","56","12"),
                "address.country",Matchers.hasItems("Spain","France","Turkiye")
        );
    }
}
