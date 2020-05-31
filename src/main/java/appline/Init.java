package appline;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Wait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {
    private static WebDriver driver;
    private static ChromeOptions options = new ChromeOptions();
    private static FirefoxOptions options1 = new FirefoxOptions();
    private static Wait<WebDriver> wait;

    public static void initWebdriver() {
        Properties properties = PropsSettings.getInstance().getProperties();
        String browser = properties.getProperty("webbrowser", "chrome");
        switch (browser) {
            case "chrome": {
                System.setProperty(properties.getProperty("chromeDriver"), properties.getProperty("driverPathChrome"));
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox": {
                System.setProperty(properties.getProperty("firefoxDriver"), properties.getProperty("driverPathFirefox"));
                options1.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new FirefoxDriver(options1);
                break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(properties.getProperty("urlDns"));
    }

    public static Wait<WebDriver> getWait() {
        return wait;
    }

    public static void setWait(Wait<WebDriver> wait) {
        Init.wait = wait;
    }

    public static ChromeOptions getOptions() {
        return options;
    }

    public static void setOptions(ChromeOptions options) {
        Init.options = options;
    }

    public static FirefoxOptions getOptions1() {
        return options1;
    }

    public static void setOptions1(FirefoxOptions options1) {
        Init.options1 = options1;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
