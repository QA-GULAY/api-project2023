package master_branch.http_request.day3_Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class GetRequest_04 {
    @Test
    public void test4(){
        String url = "https://masterbranch-9f81b37abe98.herokuapp.com/api/v1/getAllPeople";
        Response response = RestAssured.given().get(url);
        response.then().body(
                "[0].personId", Matchers.equalTo("47a303e3-b674-4dd8-91e6-8f305f76e90a"),
                "[0].name",Matchers.equalTo("Yunus"),
                "age",Matchers.equalTo("22"),
                "[0].email",Matchers.equalTo("yeg@gmail.com"),
                "[0].address.city",Matchers.equalTo("Zonguldak"),
                "[0].address.zipcode",Matchers.equalTo("67300"),
                "[0].address.country",Matchers.equalTo("Turkiye")
        );

    }
}
