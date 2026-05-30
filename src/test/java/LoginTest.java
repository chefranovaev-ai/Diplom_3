import config.AppConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

@Feature("Авторизация пользователя")
public class LoginTest extends BaseTest {

    @Test
    @Step("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Авторизация с главной страницы с помощью основной кнопки")
    public void testLoginFromMainPageButton() {
        new api.UserApiHelper().createUser(fakeName, fakeEmail, fakePassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl(AppConfig.MAIN_PAGE_URL);
        mainPage.clickLoginBtn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(fakeEmail, fakePassword);
        Assert.assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась",
                mainPage.findElement(mainPage.ORDER_BTN).isDisplayed());

    }

    @Test
    @Step("Вход через кнопку 'Личный кабинет'")
    @Description("Авторизация при попытке неавторизованного пользователя войти в ЛК из хедера")
    public void testLoginFromPersonalProfileButton() {
        new api.UserApiHelper().createUser(fakeName, fakeEmail, fakePassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl(AppConfig.MAIN_PAGE_URL);
        mainPage.clickProfileBtn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(fakeEmail, fakePassword);
        Assert.assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась",
                mainPage.findElement(mainPage.ORDER_BTN).isDisplayed());

    }

    @Test
    @Step("Вход через кнопку в форме регистрации")
    @Description("Переход к авторизации по ссылке со страницы регистрации пользователя")
    public void testLoginFromRegisterForm() {
        new api.UserApiHelper().createUser(fakeName, fakeEmail, fakePassword);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openUrl(AppConfig.REGISTER_PAGE_URL);
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(fakeEmail, fakePassword);
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась",
                mainPage.findElement(mainPage.ORDER_BTN).isDisplayed());

    }

    @Test
    @Step("Вход через кнопку в форме восстановления пароля")
    @Description("Переход к авторизации по ссылке с формы forgot-password")
    public void testLoginFromForgotPasswordForm() {
        new api.UserApiHelper().createUser(fakeName, fakeEmail, fakePassword);

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.openUrl(AppConfig.FORGOT_PASSWORD_PAGE_URL);
        forgotPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(fakeEmail, fakePassword);
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась",
                mainPage.findElement(mainPage.ORDER_BTN).isDisplayed());

    }
}

