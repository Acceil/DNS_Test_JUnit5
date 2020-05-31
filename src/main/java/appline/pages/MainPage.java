package appline.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//span[@class='cart-link__icon']")
    WebElement toCart;

    public void goToCart() {
        waitUntilClickable(toCart).click();
    }
}
