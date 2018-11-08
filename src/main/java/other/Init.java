package other;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

    public class Init{

        private static WebDriver driver;

        public static WebDriver getDriver() {
            return driver;
        }
        @Step("1.Перейти на https://www.ozon.ru/ и 2.	Развернуть окно на весь экран")
        public static void begin(boolean maximaze){
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
            if (maximaze) driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            //1.	Перейти на https://www.ozon.ru/
            driver.get((String) SingletonForProperties.getInstance().getProperties().get("base.url"));
        }

        public static void end (boolean close){
            if (close) driver.close();
        }

    }


