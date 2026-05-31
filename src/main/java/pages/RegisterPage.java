package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    public final By PASSWORD_ERROR = By.xpath(".//p[text()='Некорректный пароль']");
    private final By NAME_INPUT = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By REGISTER_BTN = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By LOGIN_LINK = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить поля регистрации и кликнуть 'Зарегистрироваться'")
    public void registerUser(String name, String email, String password) {
        inputText(NAME_INPUT, name);
        inputText(EMAIL_INPUT, email);
        inputText(PASSWORD_INPUT, password);
        clickElement(REGISTER_BTN);
    }

    @Step("Кликнуть по ссылке 'Войти' под формой регистрации")
    public void clickLoginLink() {
        clickElement(LOGIN_LINK);
    }
}

