package kata.supermarket;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void scanShouldAddItemsToTheBasket() {
        List<Promotion> promotions = List.of(new BuyOneGetOneFreePromotion("P1"));
        Basket basket = new Basket();
        Checkout checkout = new Checkout(promotions, basket);
        Item item = new Product(new BigDecimal("1.00"), "P").oneOf();

        checkout.scan(item);

        assertEquals(1, basket.items().size());
    }
}