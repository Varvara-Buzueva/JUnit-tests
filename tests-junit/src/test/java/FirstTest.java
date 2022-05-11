import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    private Logger logger = LogManager.getLogger(FirstTest.class);
    private WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver was set up!");
    }

    @After
    public void close(){
        if( driver != null){
            driver.close();
        }
    }

    @Test
    public void testLog(){
        logger.warn("Я варнинг!");
    }

    @Test
    public void openOtus(){
        driver.get("https://www.otus.ru");
    }
}
