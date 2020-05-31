package appline;

import java.util.HashMap;
import java.util.Map;

public class VirtualCart {
    private static final Map<String, Double> prices = new HashMap<>();

    public static void addToVirtualCart(String name, Double price) {
        getPrices().put(name, price);
    }

    public static Map<String, Double> getPrices() {
        return prices;
    }
}
