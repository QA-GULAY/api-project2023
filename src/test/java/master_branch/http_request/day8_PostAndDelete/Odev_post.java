package master_branch.http_request.day8_PostAndDelete;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev_post {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("herokuapp_url");
    }
    @Test
    public void postPractice() {
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "John");
        booking.put("lastname", "Jeff");
        booking.put("totalprice", 9000);
        booking.put("depositpaid", true);


        Map<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2024-03-01");
        bookingdates.put("checkout", "2024-03-11");
        booking.put("bookingdates", bookingdates);

        Response response = given().contentType(ContentType.JSON).and().body( booking).post("booking");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals( booking.get("firstname"),response.path("booking.firstname"));
        assertEquals( booking.get("lastname"),response.path("booking.lastname"));
        assertEquals( booking.get("totalprice"),response.path("booking.totalprice"));
        assertEquals( booking.get("depositpaid"),response.path("booking.depositpaid"));
        //map icinden map cagirmak icin casting yapiyoruz hashMap ile
        assertEquals(((HashMap) bookingdates.get("bookingdates.checkin")),response.path("bookingdates.checkin"));
        assertEquals(((HashMap) bookingdates.get("bookingdates.checkout")),response.path("bookingdates.checkout"));






    }
}