package master_branch.http_request.day3_Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class etut {
    @Test
    public void test1() {
        String url = " https://reqres.in/api/users?page=2 ";
        Response response = RestAssured.given().get(url);
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        response.then().body(
                "data[0].id", Matchers.equalTo(7),
                "data.email[0]", Matchers.equalTo("michael.lawson@reqres.in"),
                "data.first_name[0]", Matchers.equalTo("Michael"),
                "data.last_name[0]", Matchers.equalTo("Lawson"),
                "data.avatar[0]", Matchers.equalTo("https://reqres.in/img/faces/7-image.jpg")
        );
    }

    @Test
    public void tets2() {
        String url = " https://demoqa.com/BookStore/v1/Books ";
        Response response = RestAssured.given().get(url);
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType(" application/json; charset=utf-8");
        response.then().body(
                "books.isbn[0]", Matchers.equalTo("9781449325862"),
                "books.title[0]", Matchers.equalTo("Git Pocket Guide"),
                "books.subTitle[0]", Matchers.equalTo("A Working Introduction"),
                "books.author[0]", Matchers.equalTo("Richard E. Silverman"),
                "books.publish_date[0]", Matchers.equalTo("2020-06-04T08:48:39.000Z"),
                "books.publisher[0]", Matchers.equalTo("O'Reilly Media"),
                "books.pages[0]", Matchers.equalTo(234),
                "books.description[0]", Matchers.equalTo("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp"),
                "books.website[0]", Matchers.equalTo("http://chimera.labs.oreilly.com/books/1230000000561/index.html")
        );
    }

}
