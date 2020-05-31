package appline.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResultPage extends BasePage {

    public void chooseItem(String name) {
        String result = "//div[@class='products-list__content']//a[contains(text(),'%s')]";
        By locator = By.xpath(String.format(result, name));
        WebElement product =  driver.findElement(locator);
        waitUntilClickable(product);
        product.click();
    }
}
