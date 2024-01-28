package master_branch.http_request.day10_Authorization;

import Utulities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }
    @Test
    public void schemaValidationTest(){
    given().accept(ContentType.JSON).pathParam("id","f44d597f-b32f-468e-a1b3-aaf39c86255c").
            get("getPerson/{id}").
            then().assertThat().statusCode(200)
           . body(matchesJsonSchemaInClasspath("singleMasterPersonSchema.json"))
            ;
    }


}
