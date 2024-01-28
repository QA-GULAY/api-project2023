package master_branch;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.http_request.pojos.regres.pojo.pojos.regres.pojo.Master_BranchPojo;
import org.junit.Assert;
import org.junit.Before;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PersonEPMethods {
    @Before
    public void setUp() {

        RestAssured.baseURI = ConfigurationReader.getProperties("url");
    }



    public Response getAllPeople(){
        Response response = RestAssured.given().accept(ContentType.JSON).get("getAllPeople");
        Assert.assertEquals(200,response.statusCode());
        return response;
    }

    public Response getPerson(String personId){
        Response response = given().accept(ContentType.JSON).get("getPerson/"+personId);
        Assert.assertEquals(200,response.statusCode());
        return response;
    }

    public Response addNewPerson(Map<String,Object> requestBody){
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).
                when().post("addPerson");
        Assert.assertEquals(200,response.statusCode());
        return response;
    }

    public Response addNewPerson(Master_BranchPojo requestBody){
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).
                when().post("addPerson");
        Assert.assertEquals(200,response.statusCode());
        return response;
    }
    public Response updatePerson(Master_BranchPojo requestBody){
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).
                when().put("updatePerson");

        Assert.assertEquals(200,response.statusCode());
        return response;
    }
    public Response updatePerson(Map<String,Object> requestBody){
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).
                when().put("updatePerson");
        Assert.assertEquals(200,response.statusCode());
        return response;
    }

    public Response partialUpdatePerson(Map<String,Object> requestBody, String personId){
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).
                when().patch("partialUpdate/"+personId);
        Assert.assertEquals(200,response.statusCode());
        return response;
    }

    public Response partialUpdatePerson(Master_BranchPojo requestBody, String personId){
        Response response = given().contentType(ContentType.JSON).and().body(requestBody).
                when().patch("partialUpdate/"+personId);
        Assert.assertEquals(200,response.statusCode());
        return response;
    }
}
