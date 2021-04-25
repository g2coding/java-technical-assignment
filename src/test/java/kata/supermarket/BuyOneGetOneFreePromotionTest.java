package kata.supermarket;

import java.math.BigDecimal;
import java.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyOneGetOneFreePromotionTest {

    @Test
    void shouldNotProvideDiscountForOneItemBought() {
        BuyOneGetOneFreePromotion buyOneGetOneFreePromotion = new BuyOneGetOneFreePromotion("P1");
        List<Item> items = List.of(new Product(new BigDecimal("0.49"), "P1").oneOf());

        List<Item> itemsAfterDiscounts = buyOneGetOneFreePromotion.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

    @Test
    void shouldGiveOneProductFreeWhenTwoBought() {
        BuyOneGetOneFreePromotion buyOneGetOneFreePromotion = new BuyOneGetOneFreePromotion("P1");
        Item yoghurt = new Product(new BigDecimal("0.50"), "P1").oneOf();
        List<Item> items = List.of(yoghurt, yoghurt);

        List<Item> itemsAfterDiscounts = buyOneGetOneFreePromotion.apply(items);

        assertEquals(new BigDecimal("0.50"), itemsAfterDiscounts.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(1).discount());
    }
}