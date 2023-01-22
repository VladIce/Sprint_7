package courier;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CreateCourierTest {
    private final CourierGenerator generator = new CourierGenerator();

    private final CourierRequest request = new CourierRequest();

    private final CourierApiResponse response = new CourierApiResponse();

    private final Courier courier = generator.random();
    private int idCourier;

    @Before
    public void createAndCheckTheCourier() {
        // запрос, на создание нового курьера в системе
        ValidatableResponse creationCourier = request.create(courier);
        response.createdSuccessfully(creationCourier);
    }

    @Test
    public void requestWithDuplicateLogin() {
        // запрос, на повтроное создание сущетвующего курьера в системе
        ValidatableResponse creatingCourierWithRecurringLogin = request.create(courier);
        response.conflictWhenCreatingCourier(creatingCourierWithRecurringLogin);
    }

    @Test
    public void requestWithoutPassword(){
        // запрос, на создание нового курьера без пароля
        Credentials credentials = new Credentials(courier.getLogin());
        ValidatableResponse creatingCourierWithoutPassword = request.createFails(credentials);
        response.badRequestForCreatingCourier(creatingCourierWithoutPassword);
    }





    @After
    public void deleteCourier() {
        // запрос для получения id курьера в системе
        ValidatableResponse getLoginCourier = request.loginCourierOfSystem(courier);
        idCourier = response.loginSuccessfully(getLoginCourier);

        // Удаляем и проверяем, что курьера был удалён из системы после теста
        ValidatableResponse deleteCourier = request.deleteCourier(idCourier);
        response.deleteSuccessfully(deleteCourier);
    }
}









