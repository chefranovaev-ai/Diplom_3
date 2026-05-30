import config.AppConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.RegisterPage;

@Feature("Регистрация")
public class RegistrationTest extends BaseTest {
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    @Step("Успешная регистрация пользователя")
    @Description("Проверка возможности зарегистрировать аккаунт с корректными данными")
    public void testSuccessfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openUrl(AppConfig.REGISTER_PAGE_URL);
        registerPage.registerUser(fakeName, fakeEmail, fakePassword);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue("Форма логина не открылась", loginPage.findElement(loginPage.LOGIN_BTN).isDisplayed());
    }

    @Test
    @Step("Отображение ошибки при некорректном пароле")
    @Description("Проверка валидации: появление ошибки при длине пароля менее 6 символов")
    public void testShortPasswordError() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openUrl(AppConfig.REGISTER_PAGE_URL);
        registerPage.registerUser(fakeName, fakeEmail, "12345");

        Assert.assertTrue("Сообщение об ошибке пароля не отобразилось",
                registerPage.findElement(registerPage.PASSWORD_ERROR).isDisplayed());
    }
}

