package courier;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.RestAssured;




import static io.restassured.RestAssured.given;

public class CourierRequest {

    protected final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    protected final String ROOT = "/api/v1/courier";

    public ValidatableResponse create(Courier courier) {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse createFails(Credentials credentials) {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(credentials)
                .when()
                .post(ROOT)
                .then().log().all();
    }


    public ValidatableResponse loginCourierOfSystem(Courier courier) {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    public ValidatableResponse loginCourierOfSystem(Credentials courier) {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    public ValidatableResponse deleteCourier(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }
}
