package api;

import config.AppConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserCredentials;
import models.UserRegistrationModel;

public class UserApiHelper {

    public UserApiHelper() {

        RestAssured.baseURI = AppConfig.BASE_URL;
    }

    @Step("API Запрос: Создание пользователя с именем: {name}, email: {email}")
    public Response createUser(String name, String email, String password) {
        UserRegistrationModel registrationData = new UserRegistrationModel(name, email, password);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(registrationData)
                .post("/api/auth/register");
    }
    @Step("API Запрос: Авторизация пользователя под email {credentials.email}")
    public Response loginUser(UserCredentials credentials) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(credentials)
                .post("/api/auth/login");
    }

    @Step("API Запрос: Удаление профиля пользователя по токену")
    public Response deleteUser(String accessToken) {
        return RestAssured.given()
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }


    @Step("API Запрос: Удаление пользователя (если он был создан) по email: {email}")
    public void deleteUserIfCreated(String email, String password) {
        try {
            UserCredentials credentials = new UserCredentials(email, password);

            Response loginResponse = loginUser(credentials);

            if (loginResponse.getStatusCode() == 200) {
                String accessToken = loginResponse.then().extract().path("accessToken");
                if (accessToken != null && !accessToken.isEmpty()) {
                    deleteUser(accessToken)
                            .then()
                            .statusCode(202);
                    System.out.println("Данные успешно зачищены через API Helper (Gson).");
                }
            }
        } catch (Exception e) {
            System.out.println("Очистка через API пропущена или завершилась с ошибкой: " + e.getMessage());
        }
    }
}

