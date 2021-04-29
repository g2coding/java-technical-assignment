package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemByUnitTest {

    @Test
    void discountsShouldBeZeroWhenItemFirstCreated() {
        Item itemByUnit = new Product(new BigDecimal("10.00"), "P1").oneOf();
        assertEquals(BigDecimal.ZERO, itemByUnit.discount());
    }

    @Test
    void withPriceAfterDiscountsShouldCreateAnItemWithTheGivenPriceAfterDiscounts() {
        Item itemByUnit = new Product(new BigDecimal("10.00"), "P1").oneOf();
        Item itemAfterDiscounts = itemByUnit.withDiscount(new BigDecimal("5.00"));
        assertEquals(new BigDecimal("5.00"), itemAfterDiscounts.discount());
    }
}