import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class MtsTestPageObject {

    // СТАРЫЕ ТЕСТЫ, НАПИСАННЫЕ В Л13, ПОПЫТКА ПЕРЕДЕЛАТЬ ПОД МОДЕЛЬ Page Object
    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void nameOfBlockTest() {
        MtsPage.openPage();
        MtsPage.cookieDecline();
        String factText = MtsPage.driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText();
        String expectedText = "Онлайн пополнение\n" +
                "без комиссии";

        Assertions.assertEquals(expectedText, factText);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void paymentSystemLogoIsHereTest() {
        MtsPage.openPage();
        MtsPage.cookieDecline();
        List<WebElement> logo = MtsPage.driver.findElements(By.xpath("//div[@class='pay__partners']/ul/li"));
        Assertions.assertFalse(logo.isEmpty());
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void infoLinkTest() {
        MtsPage.openPage();
        MtsPage.cookieDecline();
        MtsPage.driver.findElement(By.xpath("//div[@class='pay__wrapper']/a")).click();
        String factUrl = MtsPage.driver.getCurrentUrl();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assertions.assertEquals(expectedUrl, factUrl);
    }

    @Test
    @DisplayName("Проверка работы кнопки 'продожить'")
    public void continueButtonTest() {
        MtsPage.openPage();
        MtsPage.cookieDecline();
        MtsPage.driver.findElement(By.xpath("//input[@id='connection-phone']")).sendKeys("297777777");
        MtsPage.driver.findElement(By.xpath("//input[@id='connection-sum']")).sendKeys("1");
        MtsPage.driver.findElement(By.xpath("//form[@class='pay-form opened']/button")).click();

        new WebDriverWait(MtsPage.driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        MtsPage.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Assertions.assertEquals("Оплата: Услуги связи\n" +
                "Номер:375" + "297777777", MtsPage.driver.findElement(By.xpath("//div[@class='pay-description__text']/span")).getAttribute("textContent"));
    }

    // НОВЫЕ ТЕСТЫ, НАПИСАННЫЕ ДЛЯ Л14. ЗАДАНИЕ 1
    @ParameterizedTest(name = "надписи в полях для варианта 'Услуги связи'")
    @ArgumentsSource(InputsArgumentsProviderUslugi.class)
    public void testArgumentsSourceUslugiSvyazi(String expectedText, String factText) {
        Assertions.assertEquals(expectedText, factText);
    }

    static class InputsArgumentsProviderUslugi implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            MtsPage.openPage();
            MtsPage.cookieDecline();
            return MtsPage.argsProviderUslugi();
        }
    }

    @ParameterizedTest(name = "надписи в полях для варианта 'Домашний интернет'")
    @ArgumentsSource(InputsArgumentsProviderDomInt.class)
    public void testArgumentsSourceDomInt(String expectedText, String factText) {
        Assertions.assertEquals(expectedText, factText);
    }

    static class InputsArgumentsProviderDomInt implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            MtsPage.openPage();
            MtsPage.cookieDecline();
            MtsPage.chooseOption();
            MtsPage.driver.findElement(By.xpath("//p[contains(text(), 'Домашний интернет')]")).click();
            return MtsPage.argsProviderDomashnyInternet();
        }
    }

    @ParameterizedTest(name = "надписи в полях для варианта 'Рассрочка'")
    @ArgumentsSource(InputsArgumentsProviderRassrochka.class)
    public void testArgumentsSourceRassrochka(String expectedText, String factText) {
        Assertions.assertEquals(expectedText, factText);
    }

    static class InputsArgumentsProviderRassrochka implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            MtsPage.openPage();
            MtsPage.cookieDecline();
            MtsPage.chooseOption();
            MtsPage.driver.findElement(By.xpath("//p[contains(text(), 'Рассрочка')]")).click();
            return MtsPage.argsProviderRassrochka();

        }
    }

    @ParameterizedTest(name = "надписи в полях для варианта 'Задолженность'")
    @ArgumentsSource(InputsArgumentsProviderZadoljennost.class)
    public void testArgumentsSourceZadoljennost(String expectedText, String factText) {
        Assertions.assertEquals(expectedText, factText);
    }

    static class InputsArgumentsProviderZadoljennost implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            MtsPage.openPage();
            MtsPage.cookieDecline();
            MtsPage.chooseOption();
            MtsPage.driver.findElement(By.xpath("//p[contains(text(), 'Задолженность')]")).click();
            return MtsPage.argsProviderZadoljennost();
        }
    }

    // Л14 ЗАДАНИЕ 2
    @Test
    @DisplayName("Проверка конпки 'продолжить' и плейсхолдеров в форме 'онлайн пополнение без комиссии'")
    public void continueButtonAndPlaceholdersTest() {
        MtsPage.openPage();
        MtsPage.cookieDecline();

        // Поправьте меня, пожалуйста, если я заблуждаюсь, но тут нет смысла закатывать шаги теста в отдельные методы в классе
        // страницы, потому что они (в моём случае) используются единоразово только в этом тесте + здесь нет каких-то сложных взаимодействий.
        // Чего нельзя сказать об открытии страницы и отмены кук, они встречаются уже 9й тест подряд, и поэтому проще вызвать прописанный метод,
        // чем копипастить тело этого метода из теста в тест. Поэтому этот тест оставил почти без изменений

        MtsPage.driver.findElement(By.xpath("//input[@id='connection-phone']")).sendKeys("297777777");
        MtsPage.driver.findElement(By.xpath("//input[@id='connection-sum']")).sendKeys("1");
        MtsPage.driver.findElement(By.xpath("//form[@class='pay-form opened']/button")).click();

        new WebDriverWait(MtsPage.driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        MtsPage.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Assertions.assertEquals("1.00 BYN", MtsPage.driver.findElement(By.xpath("//div[@class='pay-description__cost']/span[1]")).getAttribute("textContent")); // проверка коректности отображения суммы в форме
        Assertions.assertEquals(" Оплатить  1.00 BYN ", MtsPage.driver.findElement(By.xpath("//div[@class='card-page__card']/button[@type='submit']")).getAttribute("textContent")); // проверка корректности отображения суммы на кнопке
        Assertions.assertEquals("Оплата: Услуги связи\n" +
                "Номер:375297777777", MtsPage.driver.findElement(By.xpath("//div[@class='pay-description__text']/span")).getAttribute("textContent"));
        Assertions.assertEquals("Номер карты", MtsPage.driver.findElement(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']")).getAttribute("textContent"));
        Assertions.assertEquals("Срок действия", MtsPage.driver.findElement(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']")).getAttribute("textContent"));
        Assertions.assertEquals("CVC", MtsPage.driver.findElement(By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']")).getAttribute("textContent"));
        Assertions.assertEquals("Имя держателя (как на карте)", MtsPage.driver.findElement(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']")).getAttribute("textContent"));
        List<WebElement> logo = MtsPage.driver.findElements(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted']"));
        logo.add(MtsPage.driver.findElement(By.xpath("//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']")));
        Assertions.assertFalse(logo.isEmpty());

    }

}


