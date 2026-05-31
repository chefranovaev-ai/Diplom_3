import config.AppConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;

@Feature("Конструктор бургеров")
public class ConstructorTest extends BaseTest {


    @Test
    @Step("Переход к разделу 'Соусы'")
    @Description("Проверка активации вкладки соусов по изменению HTML-класса элемента")
    public void testSwitchToSauces() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl(AppConfig.MAIN_PAGE_URL);

        mainPage.switchToTab(mainPage.SAUCES_TAB);

        Assert.assertTrue("Вкладка 'Соусы' не стала активной",
                mainPage.isTabActive(mainPage.SAUCES_TAB));
    }

    @Test
    @Step("Переход к разделу 'Начинки'")
    @Description("Проверка активации вкладки начинок по изменению HTML-класса элемента")
    public void testSwitchToFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl(AppConfig.MAIN_PAGE_URL);

        mainPage.switchToTab(mainPage.FILLINGS_TAB);

        Assert.assertTrue("Вкладка 'Начинки' не стала активной",
                mainPage.isTabActive(mainPage.FILLINGS_TAB));
    }

    @Test
    @Step("Переход к разделу 'Булки'")
    @Description("Проверка возвращения статуса активности на вкладку булок после смены раздела")
    public void testSwitchToBuns() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl(AppConfig.MAIN_PAGE_URL);

        mainPage.switchToTab(mainPage.SAUCES_TAB);

        mainPage.switchToTab(mainPage.BUNS_TAB);

        Assert.assertTrue("Вкладка 'Булки' не стала активной",
                mainPage.isTabActive(mainPage.BUNS_TAB));
    }
}

