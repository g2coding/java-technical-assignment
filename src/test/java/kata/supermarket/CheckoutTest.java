package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {

    List<Promotion> promotions = List.of(new BuyOneGetOneFreePromotion("P1"));
    Basket basket = new Basket();
    Checkout checkout = new Checkout(promotions, basket);
    Item item = new Product(new BigDecimal("1.00"), "P1").oneOf();

    @Test
    void scanShouldAddItemsToTheBasket() {
        checkout.scan(item);

        assertEquals(1, basket.items().size());
    }

    @Test
    @DisplayName("Total calculates the basket total taking into account discounts")
    void totalShouldCalculateTheTotalTakingIntoAccountDiscounts() {
        checkout.scan(item);
        checkout.scan(item);

        assertEquals(new BigDecimal("1.00"), checkout.total());
    }
}