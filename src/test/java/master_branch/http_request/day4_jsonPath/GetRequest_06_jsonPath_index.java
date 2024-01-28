package master_branch.http_request.day4_jsonPath;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import master_branch.PersonEPMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetRequest_06_jsonPath_index {
    @Before
    public void setUp() {

        RestAssured.baseURI = ConfigurationReader.getProperties("url");
    }
            PersonEPMethods requestMethods=new PersonEPMethods();
    @Test
    public void getTest() {
        Response response=requestMethods.getAllPeople();
       //response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        JsonPath jsonPath=response.jsonPath();
        //7.kisini adini yazdir (JOSE2)
        System.out.println("7. kisi="+jsonPath.getString("name[6]"));

        //7.kisi VE 11. KISININ adini yazdir (JOSE2  /  Tahsin)
        //1.yol
        System.out.println("7. kisi="+jsonPath.getString("name[6]"));
        System.out.println("11. kisi="+jsonPath.getString("name[10]"));

        //2.yol
        System.out.println("7. kisi ve 11. kisilerin adlari"+ jsonPath.getList("name[6,10]"));

        //ilk 3 kisin citylerini yazdiralim
        //1.yol
        System.out.println("ilk 3 kisin sehirleri="+jsonPath.getList("address.city[0,1,2]"));

        //2.yol
        for (int i = 0; i < 3; i++) {
            System.out.println("ilk"+(i+1)+"kisinin sehri="+jsonPath.getString("address.city["+i+"]"));

        }
        //son kisinin emailini yazdiralim
        System.out.println("son kisinin emaili="+jsonPath.getString("email[-1]"));

        //Listin icinde list kontrol etme
        //"39","43","12"yaslarinin mevcut oldugunu control etme
        //1.yol
        List<String>expectedAgesList=new ArrayList<>();
        expectedAgesList.add("39");
        expectedAgesList.add("43");
        expectedAgesList.add("12");

        List<String>actualAges=jsonPath.getList("age");
        System.out.println("actualAges.size() = " + actualAges.size());
        System.out.println("actualAges="+actualAges);
        System.out.println("expectedAgesList="+expectedAgesList);
        Assert.assertTrue("yaslar mevcut degil",actualAges.containsAll(expectedAgesList));

        //2.yol expected e tekk tek add yapmak yerine listeyi topluca asList olarak atama
        List<String>expectedAgesList2= Arrays.asList("39", "43", "12");
        System.out.println("expectedAgesList2="+expectedAgesList2);
        Assert.assertTrue("yaslar ikincide mevcut degil=",actualAges.containsAll(expectedAgesList2));

    }
}