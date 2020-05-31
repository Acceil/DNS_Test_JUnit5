package appline.pages;

import appline.Init;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver = Init.getDriver();
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    public void customWait(WebElement totalPrice, WebElement changePrice) {
        String oldPrice = totalPrice.getText();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        waitUntilClickable(changePrice).click();
        wait.until(webDriver -> {
            String currentValue = totalPrice.getText();
            return !currentValue.equals(oldPrice);
        });
    }
}
