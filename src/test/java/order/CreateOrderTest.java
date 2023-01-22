package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderRequest request = new OrderRequest();

    private final OrderApiResponse response = new OrderApiResponse();

    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private ArrayList<String> color;

    public CreateOrderTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, ArrayList<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] requestBodyTheOrder() {
        return new Object [][]{
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new ArrayList<>(List.of("BLACK"))},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new ArrayList<>(List.of("GREY"))},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new ArrayList<>(List.of("BLACK", "GREY"))},
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void createCheckOrder() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse checkOrder = request.createOrder(order);
        response.createdTheOrderSuccessfully(checkOrder);

    }
}





