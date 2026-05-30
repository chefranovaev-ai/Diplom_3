package api;

import config.AppConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserCredentials;

public class UserApiSteps {

    public UserApiSteps() {
        RestAssured.baseURI = AppConfig.BASE_URL;
    }

    @Step("API Запрос: Авторизация пользователя под email {credentials.email}")
    public Response loginUser(UserCredentials credentials) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(credentials)
                .post("/api/auth/login");
    }

    @Step("API Запрос: Регистрация нового пользователя")
    public Response createUser(String name, String email, String password) {
        // Создаем тело запроса. Для этого используем класс моделей или обычный анонимный объект/мапу
        java.util.Map<String, String> userBody = new java.util.HashMap<>();
        userBody.put("name", name);
        userBody.put("email", email);
        userBody.put("password", password);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(userBody)
                .post("/api/auth/register");
    }

    @Step("API Запрос: Удаление профиля пользователя")
    public Response deleteUser(String accessToken) {
        return RestAssured.given()
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }

    @Step("API Сценарий: Попытка авторизации и удаления созданного пользователя ({email})")
    public void deleteUserIfCreated(String email, String password) {
        try {
            UserCredentials credentials = new UserCredentials(email, password);

            Response loginResponse = loginUser(credentials);

             if (loginResponse.getStatusCode() == 200) {

                String accessToken = loginResponse.then().extract().path("accessToken");

                if (accessToken != null && !accessToken.isEmpty()) {

                    deleteUser(accessToken).then().statusCode(202);
                    System.out.println("Данные успешно зачищены через API.");
                }
            }
        } catch (Exception e) {
            System.out.println("Очистка пропущена или завершилась с ошибкой: " + e.getMessage());
        }
    }
}


