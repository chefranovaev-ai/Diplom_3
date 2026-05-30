package api;

import com.google.gson.Gson;
import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserCredentials;
import models.UserRegistrationModel;

public class UserApiHelper {
    private final Gson gson = new Gson();

    public UserApiHelper() {
        RestAssured.baseURI = AppConfig.BASE_URL;
    }

    // Метод 1: Создание пользователя (3 поля через Gson)
    public Response createUser(String name, String email, String password) {
        UserRegistrationModel registrationData = new UserRegistrationModel(name, email, password);
        String jsonBody = gson.toJson(registrationData); // Сериализация объекта в JSON-строку

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post("/api/auth/register");
    }

    // Метод 2: Авторизация и удаление пользователя (2 поля через Gson)
    public void deleteUserIfCreated(String email, String password) {
        try {
            UserCredentials credentials = new UserCredentials(email, password);
            String jsonBody = gson.toJson(credentials); // Сериализация 2 полей для логина

            Response loginResponse = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                    .post("/api/auth/login");

            if (loginResponse.getStatusCode() == 200) {
                String accessToken = loginResponse.then().extract().path("accessToken");
                if (accessToken != null && !accessToken.isEmpty()) {
                    RestAssured.given()
                            .header("Authorization", accessToken)
                            .delete("/api/auth/user")
                            .then().statusCode(202);
                    System.out.println("Данные успешно зачищены через API Helper (Gson).");
                }
            }
        } catch (Exception e) {
            System.out.println("Очистка через API пропущена или завершилась с ошибкой: " + e.getMessage());
        }
    }
}

