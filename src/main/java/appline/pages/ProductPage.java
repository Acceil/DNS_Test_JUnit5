package appline.pages;

import appline.VirtualCart;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//div[@class='hidden-xs hidden-sm price-block-wrap price-block-wrap_view_desktop']//span[@class='current-price-value']")
    WebElement productPrice;

    @FindBy(xpath = "//h1[@data-product-param='name']")
    WebElement productName;

    @FindBy(xpath = "(//button[@class='buy-btn btn btn-cart btn-lg'])[1]")
    WebElement buyButton;

    public void addToCart() {
        moveToElement(buyButton);
        waitUntilClickable(buyButton).click();
    }

    public void saveEachProductPrice() {
        String correctFormatOfPrice = productPrice.getText().replaceAll("\\s+", "");
        Double price = Double.parseDouble(correctFormatOfPrice);
        VirtualCart.addToVirtualCart(productName.getText(), price);
    }
}
