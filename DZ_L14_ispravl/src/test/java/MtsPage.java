import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

public class MtsPage {
    static String url = "https://www.mts.by/";
    static WebDriver driver = new ChromeDriver();

    private static By cookieCancelButtonLocator = By.xpath("//button[@class='btn btn_gray cookie__cancel']");
    private static By placeholderSummaLocator = By.xpath("//input[@placeholder='Сумма']");
    private static By placeholderEmailLocator = By.xpath("//input[@placeholder='E-mail для отправки чека']");
    private static By blockLocator = By.xpath("//div[@class='pay__wrapper']/h2");


    public static void openPage() {
        driver.get(url);
    }

    public static void cookieDecline() {  //реализация именно такая, потому что меню с куками может появиться, а может и нет и не понятно по какому принципу оно это решает
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(cookieCancelButtonLocator));
            driver.findElement(cookieCancelButtonLocator).isEnabled();
            driver.findElement(cookieCancelButtonLocator).click();
        } catch (TimeoutException te) {
            System.out.println("блок принятия кук не появился. продолжаем");
        }
    }

    public static void chooseOption() {
        driver.findElement(By.xpath("//button[@class='select__header']")).click();
    }

    public static Stream<Arguments> argsProviderUslugi() {
        return Stream.of(
                Arguments.of("Номер телефона",
                        driver.findElement(By.xpath("//input[@placeholder='Номер телефона']")).getAttribute("placeholder")),
                Arguments.of("Сумма",
                        driver.findElement(placeholderSummaLocator).getAttribute("placeholder")),
                Arguments.of("E-mail для отправки чека",
                        driver.findElement(placeholderEmailLocator).getAttribute("placeholder"))
        );

    }

    public static Stream<Arguments> argsProviderDomashnyInternet() {
        return Stream.of(
                Arguments.of("Номер абонента",
                        MtsPage.driver.findElement(By.xpath("//input[@placeholder='Номер абонента']")).getAttribute("placeholder")),
                Arguments.of("Сумма",
                        MtsPage.driver.findElement(placeholderSummaLocator).getAttribute("placeholder")),
                Arguments.of("E-mail для отправки чека",
                        MtsPage.driver.findElement(placeholderEmailLocator).getAttribute("placeholder"))
        );

    }

    public static Stream<Arguments> argsProviderRassrochka() {
        return Stream.of(
                Arguments.of("Номер счета на 44",
                        driver.findElement(By.xpath("//input[@placeholder='Номер счета на 44']")).getAttribute("placeholder")),
                Arguments.of("Сумма",
                        driver.findElement(placeholderSummaLocator).getAttribute("placeholder")),
                Arguments.of("E-mail для отправки чека",
                        driver.findElement(placeholderEmailLocator).getAttribute("placeholder"))
        );

    }

    public static Stream<Arguments> argsProviderZadoljennost() {
        return Stream.of(
                Arguments.of("Номер счета на 2073",
                        driver.findElement(By.xpath("//input[@placeholder='Номер счета на 2073']")).getAttribute("placeholder")),
                Arguments.of("Сумма",
                        driver.findElement(placeholderSummaLocator).getAttribute("placeholder")),
                Arguments.of("E-mail для отправки чека",
                        driver.findElement(placeholderEmailLocator).getAttribute("placeholder"))
        );
    }
}
