package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

/**
 * Notes: The functionality for the BuyOneGetOneFreePromotion was completed in the time suggested.
 * The functionality for the BuyTwoForOnePoundPromotion promotion was added in time beyond that time frame.
 */
public class Checkout {

    private final List<Promotion> promotions;
    private final Basket basket;

    public Checkout(List<Promotion> promotions, Basket basket) {
        this.promotions = promotions;
        this.basket = basket;
    }

    public void scan(Item item) {
        basket.add(item);
    }

    public BigDecimal total() {
        List<Item> itemsAfterDiscounts = basket.items();
        for (Promotion promotion : promotions) {
            itemsAfterDiscounts = promotion.apply(itemsAfterDiscounts);
        }
        Basket basketAfterDiscounts = basket.withItems(itemsAfterDiscounts);
        return basketAfterDiscounts.total();
    }
}
