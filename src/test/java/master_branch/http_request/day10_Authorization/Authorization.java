package master_branch.http_request.day10_Authorization;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Authorization {
    @Before
    public void setUp(){
        baseURI= ConfigurationReader.getProperties("bookStore_url");
    }
    @Test
    public void authorizationTestByBeraerToken(){
        Response response=given().queryParam("UserId","94befb28-1fc4-4601-b7b7-922f71bb05af")
                .header("Authorization","Bearer "+generateToken())
                .and().delete("Books");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.then().assertThat().statusCode(204);


    }
    @Test
    public void basicAuthorizationTest(){
        Response response=given().queryParam("UserId","94befb28-1fc4-4601-b7b7-922f71bb05af")
                .auth().basic("admin555","Password123*") //key sayfada hazir o yuzden sadce value gonderiyoruz
                .header("Authorization","Basic YWRtaW41NTU6UGFzc3dvcmQxMjMq")//swagger sitesinden header bolumunden aliyoruz
                .and().delete("Books");
        response.then().assertThat().statusCode(204);
        System.out.println("response.statusCode() = " + response.statusCode());
    }
    private static String generateToken(){
        Map<String,String> user=new HashMap<>();
        user.put("userName","admin555");
        user.put("password","Password123*");

        Response response=given().contentType(ContentType.JSON).body(user).and()
                .post("https://demoqa.com/Account/v1/GenerateToken");
        return response.path("token");

    }
}
