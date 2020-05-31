package appline.pages;

import appline.VirtualCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    private static final String productPricePath = "(//span[@class='price__current'])[%s]";
    private static final String productNamePath = "(//div[@class='cart-items__product-name'])[%s]";
    private static final String remove = "(//*[contains(text(), 'Удалить')])[%s]";
    private static final String productName = "//a[contains(text(), '%s')]";

    @FindBy(xpath = "//div[contains(@data-commerce-target, 'basket_additional_warranty_24')]/span")
    WebElement guarantee;

    @FindBy(xpath = "//input[@class='count-buttons__input']")
    WebElement addMore;

    @FindBy(xpath = "//a[@class='empty-message-restore-btn ui-link_pseudolink']")
    WebElement returnProduct;

    @FindBy(xpath = "//div[@class='total-amount__label']//span[@class='price__current']")
    WebElement cartSum;

    public boolean checkCartSum() {
        String factSum = cartSum.getText().replaceAll("\\s+", "");
        Double factualSum = Double.parseDouble(factSum);
        Double expectedSum = VirtualCart.getPrices().values().stream().reduce(Double::sum).get();
        System.out.println(factualSum);
        System.out.println(expectedSum);
        return factualSum.equals(expectedSum);
    }

    public boolean checkProductPrice(String num) {
        By locator = By.xpath(String.format(productPricePath, num));
        By locator1 = By.xpath(String.format(productNamePath, num));

        WebElement productElement1 = driver.findElement(locator1);
        WebElement productElement = driver.findElement(locator);

        String productPrice = productElement.getText().replaceAll("\\s+", "");

        Double factualPrice = Double.parseDouble(productPrice);
        Double expectedProductPrice = VirtualCart.getPrices().get(productElement1.getText());
        return factualPrice.equals(expectedProductPrice);
    }

    public void getGuarantee() {
        customWait(cartSum, guarantee);
    }

    public void removeProduct(String productNumber) {
        By locator = By.xpath(String.format(remove, productNumber));
        By locator1 = By.xpath(String.format(productNamePath, productNumber));

        WebElement productName = driver.findElement(locator1);
        WebElement productRemove = driver.findElement(locator);

        VirtualCart.getPrices().remove(productName.getText());

        waitUntilClickable(productRemove);
        productRemove.click();
    }

    public void saveGuaranteePrice() {
        String correctFormatOfPrice = cartSum.getText().replaceAll("\\s+", "");
        Double price = Double.parseDouble(correctFormatOfPrice);
        VirtualCart.getPrices().clear();
        VirtualCart.addToVirtualCart("Сумма товаров с гарантией", price);
    }

    public void addProduct(String count) {
        waitUntilClickable(addMore).click();
        addMore.clear();
        waitUntilClickable(addMore).sendKeys(count);
        customWait(cartSum, addMore);
    }

    public void returnProduct(){
        waitUntilClickable(returnProduct).click();
    }


    public boolean checkProduct(String name) {
        By locator = By.xpath(String.format(productName, name));
        return driver.findElements(locator).isEmpty();
    }
}
