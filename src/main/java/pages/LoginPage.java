package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public final By LOGIN_BTN = By.xpath(".//button[text()='Войти']");
    private final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить форму логина и отправить")
    public void login(String email, String password) {
        inputText(EMAIL_INPUT, email);
        inputText(PASSWORD_INPUT, password);
        clickElement(LOGIN_BTN);
    }
}

