package listoforders;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ListOfOrdersRequest {

    protected final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    protected final String ROOT = "/api/v1/orders";



    public ValidatableResponse create(int id) {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(ROOT + "?courierId=" + id)
                .then().log().all();
    }
}
