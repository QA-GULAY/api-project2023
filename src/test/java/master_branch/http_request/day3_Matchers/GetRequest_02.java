package master_branch.http_request.day3_Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class GetRequest_02 {
    @Test
    public void getTest2() {
        String url = "https://masterbranch-9f81b37abe98.herokuapp.com/api/v1/getPerson/47a303e3-b674-4dd8-91e6-8f305f76e90a";
        Response response = RestAssured.given().get(url);
        response.prettyPrint();  //reponse taki body i yazdirir
        response.prettyPeek();   //response taki header lari ve body i yazdirir
        response.then().log().all();  //respond taki header ve body i yazdirir yani ustteki ile ayni

        //Assert etme
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        response.then().body(
                "personId", Matchers.equalTo("47a303e3-b674-4dd8-91e6-8f305f76e90a"),
                "name",Matchers.equalTo("Yunus"),
                "age",Matchers.equalTo("22"),
                "email",Matchers.equalTo("yeg@gmail.com"),
                "address.city",Matchers.equalTo("Zonguldak"),
                "address.zipcode",Matchers.equalTo("67300"),
                "address.country",Matchers.equalTo("Turkiye")
        );

    }
    @Test
    public void post(){
        String url = " https://restful-booker.herokuapp.com/booking";
        Response response = RestAssured.given().get(url);
        response.prettyPrint();

    }
}


