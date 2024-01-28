package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Odev {
    /*1. GET methodu ile https://hp-api.onrender.com/api/characters/students urline istekte bulunun
      2. headers lardan
                     Content-Type = application/json; charset=utf-8
                     statusCode = 200
      3. gelen response dan house="Gryffindor" olanlarinin isimlerini bir liste alin
                a. bu isimleri consol da yazdirin
                b. adetini yazdirin
      4. ve bunlari male ve female olarak gruplayip o sekilde de adet ve isimlerini yazdirin*/
    @Before
    public void setUp() {

        RestAssured.baseURI = ConfigurationReader.getProperties("onrender_url");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given().accept(ContentType.JSON).get("characters/students");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        JsonPath jsonPath = response.jsonPath();

        List<String>houseNamesGryffindor=jsonPath.getList("findAll{it.house==\"Gryffindor\"}");
        System.out.println("houseNamesGryffindor = " + houseNamesGryffindor);
        System.out.println("List uzunlugu="+houseNamesGryffindor.size());

        List<String>houseNamesGryffindor_female=jsonPath.getList("findAll{it.house==\"Gryffindor\"&& it.gender==\"female\"}");
        System.out.println("houseNamesGryffindor_female = " + houseNamesGryffindor_female);
        System.out.println("houseNamesGryffindor_female.size() = " + houseNamesGryffindor_female.size());

        List<String>houseNamesGryffindor_male=jsonPath.getList("findAll{it.house==\"Gryffindor\"&& it.gender==\"male\"}");
        System.out.println("houseNamesGryffindor_male = " + houseNamesGryffindor_male);
        System.out.println("houseNamesGryffindor_male.size() = " + houseNamesGryffindor_male.size());
    }
}