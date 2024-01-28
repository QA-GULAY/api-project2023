package master_branch.http_request.day5_response.path.pathParam;

import Utulities.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Pratik {
   /* 1. GET methodu ile https://restful-booker.herokuapp.com/booking/11 urline pathParam kullanarak istekte bulunun
            2. headers lardan
    Content-Type = application/json; charset=utf-8
    statusCode = 200
            3. gelen response danki kisiyi dogrulayin*/
   @Before
   public void setUp() {

       baseURI = ConfigurationReader.getProperties("restful_url");
   }

    @Test
    public void pathParamTest() {
       Response response=given()
                .pathParams("bookingId",11)
                .get("/{bookingId}");
        response.prettyPrint();
        response.then().assertThat()
                .contentType("application/json; charset=utf-8")
                .statusCode(200);
        String firstName=response.path("firstname");
        String lastname=response.path("lastname");
        int totalprice=response.path("totalprice");
        boolean depositpaid=response.path("depositpaid");
        String checkin=response.path("bookingdates.checkin");
        String checkout=response.path("bookingdates.checkout");
        String additionalneeds=response.path("additionalneeds");
        assertEquals("John",firstName);
        assertEquals("Smith",lastname);
        assertEquals(111,totalprice);
        assertEquals("2018-01-01",checkin);
        assertEquals(true,depositpaid);
        assertEquals("2019-01-01",checkout);
        assertEquals("Breakfast",additionalneeds);

        //2.yol
        int totalprice1 = response.path("totalprice");
        assertEquals("John",response.path("firstname"));
        assertEquals("Smith",response.path("lastname"));
        assertEquals(111, (int)(response.path("totalprice")));
        assertEquals(true,response.path("depositpaid"));
        assertEquals("2018-01-01",response.path("bookingdates.checkin"));
        assertEquals("2019-01-01",response.path("bookingdates.checkout"));

    }


















}
