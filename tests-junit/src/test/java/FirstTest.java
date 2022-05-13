import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
public class FirstTest {

    private Logger logger = LogManager.getLogger(FirstTest.class);
    private WebDriver driver;
    private ConfigServer cfg = ConfigFactory.create(ConfigServer.class);

    @Before
    public void setup(){
        Duration duration = Duration.ofSeconds(5);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(duration);
        logger.info("Driver was set up!");
    }

    @After
    public void close(){
        if( driver != null){
            driver.close();
        }
    }

    //@Test
   // public void testLog(){
      //  logger.warn("Я варнинг!");
    //}

    private void auth() throws InterruptedException{
        String login = "buzueva.varvara@mail.ru";
        String password = "k84v87a12i15o19";
        String locator = "button.header_auth.js-open-modal";
        driver.findElement(By.cssSelector(locator)).click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Авторизация прошла успешно");
    }

    private void enterLK() throws InterruptedException {
        Thread.sleep(1000);
       // String locator = ".ic-blog-default-avatar";
       // WebElement icon = driver.findElement(By.cssSelector(locator));
       // Actions actions =new Actions(driver);
       // actions.moveToElement(icon).build().perform();
      driver.get("http://otus.ru/lk/biography/personal/");
      logger.info("Перешёл в личный кабинет");
    }

    @Test
    public void openPage() throws InterruptedException{
        // п.1 Открыть Otus.ru.
        driver.get("http://otus.ru");
        logger.info("Открыта страница Otus");
        // п.2 Авторизовапться на сайте.
        auth();
        // п.3 Перейти в личный кабинет.
        enterLK();
        // п.4 В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов.
        driver.findElement(By.id("id_fname_latin")).clear();
        driver.findElement(By.id("id_lname")).clear();
        driver.findElement(By.id("id_lname_latin")).clear();
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).clear();

        driver.findElement(By.id("id_fname_latin")).sendKeys("Варвара");
        driver.findElement(By.id("id_lname")).sendKeys("Бузуева");
        driver.findElement(By.id("id_lname_latin")).sendKeys("Varvara");
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).sendKeys("29.01.1987");
          // Страна
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependen-master > label:nth-child(1) > div:nth-child(2)")).getText().contains("Россия"))
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependen-master > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(),'Россия')]")).click();
        }
         // Город
       if (!driver.findElement(By.cssSelector(".js-lk-cv-dependen-slave-city > label:nth-child(1) > div:nth-child(2)")).getText().contains("Самара"))
       {
           driver.findElement(By.cssSelector(".js-lk-cv-dependen-slave-city > label:nth-child(1) > div:nth-child(2)")).click();
           driver.findElement(By.xpath("//*[contains(text(),'Самара')]")).click();
       }
    }
}
