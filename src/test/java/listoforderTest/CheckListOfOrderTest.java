package listoforderTest;

import courier.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import listoforders.ListOfOrderApiResponse;
import listoforders.ListOfOrdersRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckListOfOrderTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final Courier courier = generator.random();

    private final CourierRequest requestCourier = new CourierRequest();

    private final CourierApiResponse responseCourier = new CourierApiResponse();
    private final ListOfOrdersRequest request = new ListOfOrdersRequest();

    private final ListOfOrderApiResponse response = new ListOfOrderApiResponse();
    private final  Credentials credentials = new Credentials(courier.getLogin(), courier.getPassword());

    private int idCourier;


    @Before
    public void createAndLoginCourier() {
        // запрос, на создание нового курьера в системе
        ValidatableResponse creationCourier = requestCourier.create(courier);
        responseCourier.createdSuccessfully(creationCourier);

        // авторизация курьера в системе
        ValidatableResponse authorizationCourier = requestCourier.loginCourierOfSystem(credentials);
        idCourier = responseCourier.loginSuccessfully(authorizationCourier);

    }

    @Test
    @DisplayName("Получение списка заказов")
    public void checkingTheOrderList() {
        // Получение списка заказов
        ValidatableResponse checkingTheOrderList = request.create(idCourier);
        response.checkingTheOrderList(checkingTheOrderList);
    }


    @After
    public void deleteCourier() {
        // Удаляем и проверяем, что курьера был удалён из системы после теста
        ValidatableResponse deleteCourier = requestCourier.deleteCourier(idCourier);
        responseCourier.deleteSuccessfully(deleteCourier);
    }
}
