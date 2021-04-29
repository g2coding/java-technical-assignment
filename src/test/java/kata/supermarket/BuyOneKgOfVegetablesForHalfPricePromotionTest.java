package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BuyOneKgOfVegetablesForHalfPricePromotionTest {

    BuyOneKgOfVegetablesForHalfPricePromotion buyOneKgOfVegetablesForHalfPricePromotion = new BuyOneKgOfVegetablesForHalfPricePromotion("V");

    @Test
    void shouldNotApplyPromotionForLessThanAKgOfVegetables() {
        List<Item> items = List.of(new WeighedProduct(new BigDecimal("2.00"), "V").weighing(new BigDecimal("0.50")));
        List<Item> itemsAfterDiscounts = buyOneKgOfVegetablesForHalfPricePromotion.apply(items);
        assertEquals(1, itemsAfterDiscounts.size());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

    @Test
    void shouldApplyPromotionForMoreThanAKgOfVegetables() {
        List<Item> items = List.of(new WeighedProduct(new BigDecimal("2.00"), "V").weighing(new BigDecimal("1.50")));
        List<Item> itemsAfterDiscounts = buyOneKgOfVegetablesForHalfPricePromotion.apply(items);
        assertEquals(1, itemsAfterDiscounts.size());
        assertEquals(new BigDecimal("1.50"), itemsAfterDiscounts.get(0).discount());
    }
}