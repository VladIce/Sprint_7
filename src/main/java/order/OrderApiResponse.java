package order;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.greaterThan;

public class OrderApiResponse {

    // ответ от сервер, при успешном создании нового заказа
    public void createdTheOrderSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("track", greaterThan(0))
        ;
    }
}
