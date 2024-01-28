package master_branch.http_request.day6_De_serialization;

import Utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import master_branch.PersonEPMethods;
import master_branch.http_request.pojos.regres.pojo.Address;
import master_branch.http_request.pojos.regres.pojo.Master_branchPojo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetPojoTest_14 {
    @Before
    public void setUp() {

        baseURI = ConfigurationReader.getProperties("url");
    }
    PersonEPMethods epMethods=new PersonEPMethods();
    @Test
    public void getTest() {
        Response response =epMethods.getPerson("561e90f0-97d0-44b2-bbe2-c3d545cde661");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        Master_branchPojo person=response.as(Master_branchPojo.class);
        // System.out.println("person = " + person);
        //  System.out.println("person.toString() = " + person.toString());
        //   bu sekillerde yazdirmaya calissak verileri tek tek degil classin id uzantisini getirir
        //verilere tek tek ordaki metodu cagirarak ulasabiliriz
        System.out.println("person.getName() = " + person.getName());
        System.out.println("person.getName() = " + person.getPersonId());
        System.out.println("person.getName() = " + person.getAge());
        System.out.println("person.getName() = " + person.getEmail());
        //verileri toplu sekilde getirip yazdirmak istiyorsak classa gidip generate-->toString()diyip metod olusturup
        // burda response olarak verdigimiz isimle cagirarak listeyi yazdirabiliriz
        System.out.println("person = " + person);

        Assert.assertEquals("561e90f0-97d0-44b2-bbe2-c3d545cde661",person.getPersonId());
        Assert.assertEquals("Okan",person.getName());
        Assert.assertEquals("56",person.getAge());
        Assert.assertEquals("okan@gmail.com",person.getEmail());
        Assert.assertEquals("Artvin",person.getAddress().getCity());
        Assert.assertEquals("67300",person.getAddress().getZipcode());
        Assert.assertEquals("Turkiye",person.getAddress().getCountry());
    }
    @Test
    public void getTest2() {
        Response response = RestAssured.given().accept(ContentType.JSON).get("getPerson/561e90f0-97d0-44b2-bbe2-c3d545cde661");
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 ");
        Master_branchPojo actualPerson=response.as(Master_branchPojo.class);

        Address address=new Address("Artvin","67300","Turkiye");

        Master_branchPojo expectedPerson=new Master_branchPojo("561e90f0-97d0-44b2-bbe2-c3d545cde661","Okan","okan@gmail.com","56",address);

      //  Assert.assertEquals(expectedPerson,actualPerson);iki classi direkt karsilastiramayiz,
        //  sirkette boyle istemez tekk tek dogrulama ister
        Assert.assertEquals(expectedPerson.getPersonId(),actualPerson.getPersonId());
        Assert.assertEquals(expectedPerson.getName(),actualPerson.getName());
        Assert.assertEquals(expectedPerson.getAge(),actualPerson.getAge());
        Assert.assertEquals(expectedPerson.getEmail(),actualPerson.getEmail());

        Assert.assertEquals(expectedPerson.getAddress().getCity(),actualPerson.getAddress().getCity());
        Assert.assertEquals(expectedPerson.getAddress().getZipcode(),actualPerson.getAddress().getZipcode());
        Assert.assertEquals(expectedPerson.getAddress().getCountry(),actualPerson.getAddress().getCountry());

    }
}