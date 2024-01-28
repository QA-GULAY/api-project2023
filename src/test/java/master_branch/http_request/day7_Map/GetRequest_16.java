package master_branch.http_request.day7_Map;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.testDatas.TestMasterBranchData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetRequest_16 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }
    @Test
    public void getTest() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "3db8e8a7-548c-4d75-97fc-ed1c4c8e082b").get("getPerson/{id}");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("personId", "3db8e8a7-548c-4d75-97fc-ed1c4c8e082b");
        expectedData.put("name", "Ahmet");
        expectedData.put("age", "33");
        expectedData.put("email", "ahmet28@gmail.com");

        Map<String, Object> address = new HashMap<>();
        address.put("city", "Ankara");
        address.put("zipcode", "88100");
        address.put("country", "Turkiye");
        //IKI MAP I BIRLESTIRIYORUZ
        expectedData.put("address", address);
        System.out.println("address = " + address);
       //gelen response u map a convert ediyoruz
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.get("personId"), actualData.get("personId"));
        Assert.assertEquals(expectedData.get("name"), actualData.get("name"));
        Assert.assertEquals(expectedData.get("age"), actualData.get("age"));
        Assert.assertEquals(expectedData.get("email"), actualData.get("email"));

        Assert.assertEquals(((HashMap) expectedData.get("address")).get("city"), ((HashMap) actualData.get("address")).get("city"));
        Assert.assertEquals(((HashMap) expectedData.get("address")).get("zipcode"), ((HashMap) actualData.get("address")).get("zipcode"));
        Assert.assertEquals(((HashMap) expectedData.get("address")).get("country"), ((HashMap) actualData.get("address")).get("country"));
    }
        @Test
        public void getTest2() {  //is yeri versiyonu
            Response response =given().accept(ContentType.JSON)
                    .pathParam("id","3db8e8a7-548c-4d75-97fc-ed1c4c8e082b").get("getPerson/{id}");
            response.prettyPrint();
            response.then().assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 ");

            //MAP LARI BASKA CLASSTA OLUSTURUP BURDA O CLASSI OBJE OLUSTURUP CAGIRIYORUZ
            TestMasterBranchData data=new TestMasterBranchData();

            Map<String,Object>expectedData=data.masterBranchData();
            Map<String,Object>actualData=response.as(Map.class);
            Assert.assertEquals(expectedData.get("personId"), actualData.get("personId"));
            Assert.assertEquals(expectedData.get("name"), actualData.get("name"));
            Assert.assertEquals(expectedData.get("age"), actualData.get("age"));
            Assert.assertEquals(expectedData.get("email"), actualData.get("email"));

            Assert.assertEquals(((HashMap) expectedData.get("address")).get("city"), ((HashMap) actualData.get("address")).get("city"));
            Assert.assertEquals(((HashMap) expectedData.get("address")).get("zipcode"), ((HashMap) actualData.get("address")).get("zipcode"));
            Assert.assertEquals(((HashMap) expectedData.get("address")).get("country"), ((HashMap) actualData.get("address")).get("country"));
        }
    }
