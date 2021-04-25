package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {

    private final List<Promotion> promotions;
    private final Basket basket = new Basket();

    public Checkout(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void scan(Product product) {
    }

    public BigDecimal total() {
        return null;
    }
}