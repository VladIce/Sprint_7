package couriertest;

import courier.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CouriersLoginTest {

    private final CourierGenerator generator = new CourierGenerator();

    private final CourierApiResponse response = new CourierApiResponse();

    private final Courier courier = generator.random();

    private final CourierRequest request = new CourierRequest();

    private final Credentials credentials = new Credentials(courier.getLogin(), courier.getPassword());

    private int idCourier;

    @Before
    public void createAndLoginCourier(){
        // запрос, на создание нового курьера в системе
        ValidatableResponse creationCourier = request.create(courier);
        response.createdSuccessfully(creationCourier);

        // авторизация курьера в системе
        ValidatableResponse authorizationCourier = request.loginCourierOfSystem(credentials);
        idCourier = response.loginSuccessfully(authorizationCourier);
    }

    @Test
    @DisplayName("авторизация с невалидным паролем")
    public void authorizationWithInvalidPassword() {
        // авторизация с невалидным паролем
        Credentials credentials2 = new Credentials(courier.getLogin(), credentials.invalidPassword());
        ValidatableResponse authorizationWithInvalidPassword = request.loginCourierOfSystem(credentials2);
        response.accountNotFound(authorizationWithInvalidPassword);
    }

    @Test
    @DisplayName("авторизация курьера без пароля")
    public void authorizationWithoutAPassword() {
        // авторизация курьера без пароля
        Credentials credentials1 = new Credentials(courier.getLogin());
        ValidatableResponse authorizationWithoutAPassword = request.loginCourierOfSystem(credentials1);
        response.badRequestLoginCourier(authorizationWithoutAPassword);
    }


    @After
    public void deleteCourier() {
        // Удаляем и проверяем, что курьера был удалён из системы после теста
        ValidatableResponse deleteCourier = request.deleteCourier(idCourier);
        response.deleteSuccessfully(deleteCourier);
    }
}
