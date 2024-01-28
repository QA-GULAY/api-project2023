package master_branch.http_request.day5_response.path;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest_13 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("regres_url");
    }

    @Test
    public void pathParamTest() {
        //PATHPARAMS TA BI FARK VAR GET ICINDE PARAM I BELIRTMEMIZ GEREKIYOR (VIDEO 5 1SAAT 8 DK LARDA)
        Response response=given().accept(ContentType.JSON)
                .pathParams("pathId",12)
                .get("/{pathId}");
        response.prettyPrint();
        response.then().assertThat()
                .contentType("application/json")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
        String firstName=response.path("data.first_name");
        System.out.println("firstName = " + firstName);
        Assert.assertEquals("Rachel",firstName);

        //response u sout icinde yazdirmak istiyorsak toString diye belirtmemiz gerekiyor
        System.out.println("lastName = " +response.path("data.last_name").toString());

    }
}