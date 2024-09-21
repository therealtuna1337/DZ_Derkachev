// в этом классе попытался перевести тесты из предыдущей домашки в Page Object

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByPage {

    String url = "https://www.mts.by/";
    int phone = 297777777;
    By blockLocator = By.xpath("//div[@class='pay__wrapper']/h2");
    By logoLocator = By.xpath("//div[@class='pay__partners']/ul/li");
    By infoLinkLocator = By.xpath("//div[@class='pay__wrapper']/a");
    By cookieCancelButtonLocator = By.xpath("//button[@class='btn btn_gray cookie__cancel']");
    WebDriver driver = new ChromeDriver();

    @Test
    public void nameOfBlockTest() {
        System.setProperty("webdriver.chome.driver", "src/main/resources/chromedriver");

        driver.get(url);
        String factText = driver.findElement(blockLocator).getText();

        String expectedText = "Онлайн пополнение\n" +
                "без комиссии";

        Assertions.assertEquals(expectedText, factText);
        driver.close();
    }

    @Test
    public void paymentSystemLogoIsHereTest() {
        driver.get(url);
        driver.findElement(cookieCancelButtonLocator).click(); // не во всех случаях появляется окошко с предложением принять куки. Эта строчка для его пропуска
        List<WebElement> logo = driver.findElements(logoLocator);
        Assertions.assertFalse(logo.isEmpty());
        driver.close();
    }

    @Test
    public void infoLinkTest() {

        driver.get(url);
        driver.findElement(infoLinkLocator).click();
        String factUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

        Assertions.assertEquals(expectedUrl, factUrl);
        driver.close();

    }

    @Test
    public void continueButtonTest() {
        driver.get(url);
        driver.findElement(cookieCancelButtonLocator).click(); // не во всех случаях появляется окошко с предложением принять куки. Эта строчка для его пропуска
        driver.findElement(By.xpath("//input[@id='connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id='connection-sum']")).sendKeys("1");
        driver.findElement(By.xpath("//form[@class='pay-form opened']/button")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Assertions.assertEquals("Оплата: Услуги связи\n"+
        "Номер:375" + phone, driver.findElement(By.xpath("//div[@class='pay-description__text']/span")).getAttribute("textContent"));
        driver.quit();
    }
}
