package listoforders;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.notNullValue;

public class ListOfOrderApiResponse {

    public void checkingTheOrderList(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("orders", notNullValue())
        ;
    }


}
