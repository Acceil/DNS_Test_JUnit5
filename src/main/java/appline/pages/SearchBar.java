package appline.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBar extends BasePage {
    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchInput;

    @FindBy(xpath = "//nav[@id = 'header-search']//div[@class='ui-input-search__buttons']/span[2]")
    WebElement searchButton;

    public void search(String text) {
        waitUntilClickable(searchInput).sendKeys(text);
        waitUntilClickable(searchButton).click();
    }
}
