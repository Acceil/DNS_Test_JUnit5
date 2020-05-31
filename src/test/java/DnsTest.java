import appline.pages.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DnsTest extends PapaTest {
    @ParameterizedTest
    @MethodSource("productParameters")
    public void mainTest(String firstProductName, String secondProductName) {
        SearchBar searchBar = new SearchBar();
        searchBar.search(firstProductName);

        ResultPage resultPage = new ResultPage();
        resultPage.chooseItem(firstProductName);

        ProductPage productPage = new ProductPage();
        productPage.saveEachProductPrice();
        productPage.addToCart();

        searchBar.search(secondProductName);
        productPage.saveEachProductPrice();
        productPage.addToCart();

        MainPage mainPage = new MainPage();
        mainPage.goToCart();

        CartPage cartPage = new CartPage();
        assertThat("Сумма не равна!", cartPage.checkCartSum());
        assertThat("Цена на первый товар не равна", cartPage.checkProductPrice("1"));
        assertThat("Цена на второй товар не равна", cartPage.checkProductPrice("2"));
        cartPage.getGuarantee();
        cartPage.saveGuaranteePrice();
        cartPage.removeProduct("2");
        cartPage.getGuarantee();
        cartPage.saveGuaranteePrice();
        assertThat("Товар не был удален!", cartPage.checkProduct("Detroit"));
        assertThat("Цена после удаления не уменьшилась!", cartPage.checkCartSum());
        cartPage.addProduct("3");
        cartPage.saveGuaranteePrice();
        assertThat("Цена после добавления не увеличилась!", cartPage.checkCartSum());
        cartPage.removeProduct("1");
        cartPage.returnProduct();
        assertThat("Цена и гарантия после возвращения изменились!", cartPage.checkCartSum());
    }

    static Stream<Arguments> productParameters() {
        return Stream.of(
                arguments("PlayStation 4 Slim Black", "Detroit")
        );
    }
}
