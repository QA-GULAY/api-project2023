package master_branch.testDatas;

import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMasterBranchData {
    public Map<String, Object> masterBranchData() {
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

        return expectedData;
    }

    public JSONObject getRegresDataByJyson() {  //jsonObject Map gibi bi kullaniliyor verileri depolamak icin
        JSONObject jsonObject = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", 12);
        dataJson.put("email", "rachel.howell@reqres.in");
        dataJson.put("first_name", "Rachel");
        dataJson.put("last_name", "Howell");
        dataJson.put("avatar", "https://reqres.in/img/faces/12-image.jpg");

        JSONObject supportJson = new JSONObject();
        supportJson.put("url", "https://reqres.in/#support-heading");
        supportJson.put("text", "To keep ReqRes free, contributions towards server costs are appreciated!");

        //SIMDI DATAjSON VE SUPPORTJSON I JSONOBJECT E ATIYORUZ
        // ,key olarak gelen response daki veri eklenmeli ("data") gibi
        jsonObject.put("data", dataJson);
        jsonObject.put("support", supportJson);
        return jsonObject;

    }

    public Map<String, Object> bookingBody() {
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "John");
        booking.put("lastname", "Jeff");
        booking.put("totalprice", 900);
        booking.put("depositpaid", true);


        Map<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2024-03-01");
        bookingdates.put("checkout", "204-03-11");
        booking.put("bookingdates", bookingdates);
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("booking", booking);
        return expectedData;
    }

    @Test
    public Map<String, Object> createTest() {
        Map<String, Object> dummyData = new HashMap<>();
        dummyData.put("name", "Eser");
        dummyData.put("salary", "20000");
        dummyData.put("age", "40");
        return dummyData;
    }

    @Test
    public Map<String, Object> createExpexdetData() {
        Map<String, Object> dummyExpectedData = new HashMap<>();
        dummyExpectedData.put("status", "success");
        dummyExpectedData.put("message", "Successfully! Record has been added.");
        Map<String, Object> data = new HashMap<>();
        data.put("name","Eser");
        data.put("salary","20000");
        data.put("age","40");
        data.put("name","Eser");
        dummyExpectedData.put("data",data);
        return dummyExpectedData;
    }


    @Test
    public Map<String, Object> createTestMessage() {
        Map<String, Object>createResponseMessage = new HashMap<>();
        createResponseMessage.put("status", "success");
        createResponseMessage.put("message", "Successfully! Record has been added.");
        Map<String, Object>data = new HashMap<>();
        data.put( "id", 9344);
        createResponseMessage.put("data",data);
        return createResponseMessage;
    }

    }
