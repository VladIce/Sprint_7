package courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourierApiResponse {

    // ответ от сервера при успешном создании курьера
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true))
        ;
    }
    // ответ от сервера, если в теле запросе в ключе login передать сущетвующий логин
    public void conflictWhenCreatingCourier(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"))
        ;
    }
    // ответ от сервера, если в теле запроса не передать логин или пароль
    public void badRequestForCreatingCourier(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
        ;
    }
    // ответ от сервера, при успешном входе в систему
    public int loginSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract().path("id")
                ;
    }

    // ответ от сервера, если в теле запроса не передать логин или пароль
    public void badRequestLoginCourier(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"))
        ;
    }

    // ответ от сервевера, если в теле запроса указан несуществующим логин или пароль
    public void accountNotFound(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"))
        ;
    }
    // ответ от сервера, при успешном удалении курьера
    public void deleteSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("ok", is(true))
        ;
    }
}
