package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    public final By LOGIN_ACCOUNT_BTN = By.xpath(".//button[text()='Войти в аккаунт']");
    public final By PERSONAL_PROFILE_BTN = By.xpath(".//p[text()='Личный Кабинет']");
    public final By MAKE_BURGER_HEADER = By.xpath(".//h1[text()='Соберите бургер']");
    public final By ORDER_BTN = By.xpath(".//button[text()='Оформить заказ']");

    public final By BUNS_TAB = By.xpath(".//span[text()='Булки']/parent::div");
    public final By SAUCES_TAB = By.xpath(".//span[text()='Соусы']/parent::div");
    public final By FILLINGS_TAB = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на кнопку 'Войти в аккаунт' на главной")
    public void clickLoginBtn() {
        clickElement(LOGIN_ACCOUNT_BTN);
    }

    @Step("Нажать на кнопку 'Личный Кабинет' в шапке сайта")
    public void clickProfileBtn() {
        clickElement(PERSONAL_PROFILE_BTN);
    }

    @Step("Переключиться на вкладку конструктора")
    public void switchToTab(By tabLocator) {
       WebElement tabElement = findElement(tabLocator);

       new Actions(driver)
            .scrollToElement(tabElement)
            .click(tabElement)
            .perform();
    }
    @Step("Дождаться активации вкладки и проверить наличие активного класса")
    public boolean isTabActive(By tabLocator) {
        return wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));

    }

}
