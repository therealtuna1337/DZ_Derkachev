import org.junit.jupiter.api.Assertions;
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

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class MtsTest {

    static WebDriver driver = new ChromeDriver();

    @ParameterizedTest(name = "надписи в полях для варианта 'Услуги связи'")
    @ArgumentsSource(InputsArgumentsProviderUslugi.class)
    public void testArgumentsSourceUslugiSvyazi(String expectedText, String factText) {
        Assertions.assertEquals(expectedText, factText);
    }

    static class InputsArgumentsProviderUslugi implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            driver.get("https://www.mts.by/");
            return Stream.of(
                    Arguments.of("Номер телефона",
                            driver.findElement(By.xpath("//input[@placeholder='Номер телефона']")).getAttribute("placeholder")),
                    Arguments.of("Сумма",
                            driver.findElement(By.xpath("//input[@placeholder='Сумма']")).getAttribute("placeholder")),
                    Arguments.of("E-mail для отправки чека",
                            driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']")).getAttribute("placeholder"))
            );
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
            driver.get("https://www.mts.by/");
            driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']")).click(); //Строчка для отклонения кук
            driver.findElement(By.xpath("//button[@class='select__header']")).click();
            driver.findElement(By.xpath("//p[contains(text(), 'Домашний интернет')]")).click();
            return Stream.of(
                    Arguments.of("Номер абонента",
                            driver.findElement(By.xpath("//input[@placeholder='Номер абонента']")).getAttribute("placeholder")),
                    Arguments.of("Сумма",
                            driver.findElement(By.xpath("//input[@placeholder='Сумма']")).getAttribute("placeholder")),
                    Arguments.of("E-mail для отправки чека",
                            driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']")).getAttribute("placeholder"))
            );
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
            driver.get("https://www.mts.by/");
            driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']")).click(); //Строчка для отклонения кук
            driver.findElement(By.xpath("//button[@class='select__header']")).click();
            driver.findElement(By.xpath("//p[contains(text(), 'Рассрочка')]")).click();
            return Stream.of(
                    Arguments.of("Номер счета на 44",
                            driver.findElement(By.xpath("//input[@placeholder='Номер счета на 44']")).getAttribute("placeholder")),
                    Arguments.of("Сумма",
                            driver.findElement(By.xpath("//input[@placeholder='Сумма']")).getAttribute("placeholder")),
                    Arguments.of("E-mail для отправки чека",
                            driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']")).getAttribute("placeholder"))
            );
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
            driver.get("https://www.mts.by/");
            driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']")).click(); //Строчка для отклонения кук
            driver.findElement(By.xpath("//button[@class='select__header']")).click();
            driver.findElement(By.xpath("//p[contains(text(), 'Рассрочка')]")).click();
            return Stream.of(
                    Arguments.of("Номер счета на 2073",
                            driver.findElement(By.xpath("//input[@placeholder='Номер счета на 2073']")).getAttribute("placeholder")),
                    Arguments.of("Сумма",
                            driver.findElement(By.xpath("//input[@placeholder='Сумма']")).getAttribute("placeholder")),
                    Arguments.of("E-mail для отправки чека",
                            driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']")).getAttribute("placeholder"))
            );
        }
    }

    @Test
    @DisplayName("Задание 2")
    public void continueButtonTest() {
        driver.get("https://www.mts.by/");
        driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']")).click(); // не во всех случаях появляется окошко с предложением принять куки. Эта строчка для его пропуска
        driver.findElement(By.xpath("//input[@id='connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id='connection-sum']")).sendKeys("1");
        driver.findElement(By.xpath("//form[@class='pay-form opened']/button")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Assertions.assertEquals("1.00 BYN", driver.findElement(By.xpath("//div[@class='pay-description__cost']/span[1]")).getAttribute("textContent")); // проверка коректности отображения суммы в форме
        Assertions.assertEquals(" Оплатить  1.00 BYN ", driver.findElement(By.xpath("//div[@class='card-page__card']/button[@type='submit']")).getAttribute("textContent")); // проверка корректности отображения суммы на кнопке
        Assertions.assertEquals("Оплата: Услуги связи\n" +
                "Номер:375297777777", driver.findElement(By.xpath("//div[@class='pay-description__text']/span")).getAttribute("textContent"));
        Assertions.assertEquals("Номер карты", driver.findElement(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']")).getAttribute("textContent"));
        Assertions.assertEquals("Срок действия", driver.findElement(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']")).getAttribute("textContent"));
        Assertions.assertEquals("CVC", driver.findElement(By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']")).getAttribute("textContent"));
        Assertions.assertEquals("Имя держателя (как на карте)", driver.findElement(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']")).getAttribute("textContent"));
        List<WebElement> logo = driver.findElements(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted']"));
        logo.add(driver.findElement(By.xpath("//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']")));
        Assertions.assertFalse(logo.isEmpty());

        driver.quit();
    }

}


