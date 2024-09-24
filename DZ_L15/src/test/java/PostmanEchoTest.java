import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostmanEchoTest {

    @Test
    public void getRequestTest() {
        given()
                .contentType("application/json")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .baseUri("https://postman-echo.com/")
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("args.foo1", equalTo("bar1"))
                .and().body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void postRawTextTest() {
        given()
                .contentType("text/plain")
                .body("{\n" +
                        "    \"test\": \"value\"\n" +
                        "}")
                .baseUri("https://postman-echo.com/post")
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("data", equalTo("{\n" +
                        "    \"test\": \"value\"\n" +
                        "}"));
    }

    @Test
    public void postFormDataTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .baseUri("https://postman-echo.com/post")
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("form.foo1", equalTo("bar1"))
                .and().body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void putTest() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .baseUri("https://postman-echo.com/put")
                .when()
                .put()
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    public void patchTest() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .baseUri("https://postman-echo.com/patch")
                .when()
                .patch()
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    public void deleteTest() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .baseUri("https://postman-echo.com/delete")
                .when()
                .delete()
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("data", equalTo("This is expected to be sent back as part of response body."));
    }

}
