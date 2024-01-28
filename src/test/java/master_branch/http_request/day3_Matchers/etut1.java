package master_branch.http_request.day3_Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class etut1 {
    @Test
public void tets3(){
    String url="https://reqres.in/api/users/11 ";
    Response res= RestAssured.given().get(url);
    res.prettyPrint();
    res.then().assertThat()
            .statusCode(200)
            .contentType(" application/json; charset=utf-8");
    res.then().body(
            "data.id", Matchers.equalTo(11),
            "data.email", Matchers.equalTo("george.edwards@reqres.in"),
            "data.first_name", Matchers.equalTo("George"),
            "data.last_name", Matchers.equalTo("Edwards"),
            "data.avatar", Matchers.equalTo("https://reqres.in/img/faces/11-image.jpg")
    );



}
}
