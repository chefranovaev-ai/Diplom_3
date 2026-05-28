package pages;

import config.AppConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final String baseUrl = AppConfig.BASE_URL;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть относительный URL: {relativeUrl}")
    public void openUrl(String relativeUrl) {
        driver.get(baseUrl + relativeUrl);
    }

    @Step("Ожидание видимости элемента {locator}")
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Кликнуть по элементу")
    public void clickElement(By locator) {
        findElement(locator).click();
    }

    @Step("Ввести текст в текстовое поле")
    public void inputText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }
}
