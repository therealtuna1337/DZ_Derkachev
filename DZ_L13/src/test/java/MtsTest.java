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

public class MtsTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void nameOfBlockTest() {
        System.setProperty("webdriver.chome.driver", "src/main/resources/chromedriver");

        driver.get("https://www.mts.by/");
        String factText = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText();

        String expectedText = "Онлайн пополнение\n" +
                "без комиссии";

        Assertions.assertEquals(expectedText, factText);
        driver.close();
    }

    @Test
    public void paymentSystemLogoIsHereTest() {
        driver.get("https://www.mts.by/");
        //   driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']")).click(); // не во всех случаях появляется окошко с предложением принять куки. Эта строчка для его пропуска
        List<WebElement> logo = driver.findElements(By.xpath("//div[@class='pay__partners']/ul/li"));
        Assertions.assertFalse(logo.isEmpty());
        driver.close();
    }

    @Test
    public void infoLinkTest() {

        driver.get("https://www.mts.by/");
        driver.findElement(By.xpath("//div[@class='pay__wrapper']/a")).click();
        String factUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

        Assertions.assertEquals(expectedUrl, factUrl);
        driver.close();

    }

    @Test
    public void continueButtonTest() {
        driver.get("https://www.mts.by/");
        //   driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']")).click(); // не во всех случаях появляется окошко с предложением принять куки. Эта строчка для его пропуска
        driver.findElement(By.xpath("//input[@id='connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id='connection-sum']")).sendKeys("1");
        driver.findElement(By.xpath("//form[@class='pay-form opened']/button")).click();
        //   WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//svg-icon[@class='header__close-icon']"))); //я собирался делать явное ожидание, но, по неизвестной мне причине, драйвер в упор не видит появляющуюся форму
        //   WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@rel='noopener noreferrer']")));  //пробовал по-всякому. и элементы разные пытался искать, вставлял даже xpath, который предлагает сам браузер. эффекта ноль
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='app-wrapper']")).isDisplayed()); // тут тоже драйвер не видит элемент. тест прговаливается. почему - не знаю. пробовал искать через css-селекор. не помогло
        driver.quit();
    }

}