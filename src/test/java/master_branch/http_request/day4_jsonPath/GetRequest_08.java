package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest_08 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("regres_url");
    }

    @Test
    public void getTest() {
        Response response=given().accept(ContentType.JSON).get();
        //response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");
        JsonPath jsonPath=response.jsonPath();

        //1.yol

        int CharlesId=jsonPath.getInt("data[4].id");
        System.out.println("CharlesId = " + CharlesId);
        String CharlesName=jsonPath.getString("data[4].first_name");
        System.out.println("CharlesName = " + CharlesName);
        String CarlesEmail=jsonPath.getString("data[4].email");
        System.out.println("CarlesEmail = " + CarlesEmail);
        String CarlesLastName=jsonPath.getString("data[4].last_name");
        System.out.println("CarlesLastName = " + CarlesLastName);
        String CarlesAvatar=jsonPath.getString("data[4].avatar");
        System.out.println("CarlesAvatar = " + CarlesAvatar);

        Assert.assertEquals(5,CharlesId);
        Assert.assertEquals("Charles", CharlesName);
        Assert.assertEquals("charles.morris@reqres.in", CarlesEmail);
        Assert.assertEquals("Morris",CarlesLastName);
        Assert.assertEquals("https://reqres.in/img/faces/5-image.jpg", CarlesAvatar);

        //2.yol

        int CharlesId2=jsonPath.getInt("data.find{it.id==5}.id");
        String CharlesName2=jsonPath.getString("data.find{it.id==5}.first_name");
        String CarlesLastName2=jsonPath.getString("data.find{it.id==5}.last_name");
        String CarlesEmail2=jsonPath.getString("data.find{it.id==5}.email");
        String CarlesAvatar2=jsonPath.getString("data.find{it.id==5}.avatar");
        System.out.println("CarlesLastName2 = " + CarlesLastName2);
        System.out.println("CharlesId2 = " + CharlesId2);

        //ID SI 4 TEN BUYUK OLANLARIN ID SINI YAZDIRMA
        System.out.println(jsonPath.getList("data.findAll{it.id>4}.last_name"));
    }
}