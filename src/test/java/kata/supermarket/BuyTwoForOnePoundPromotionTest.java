package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyTwoForOnePoundPromotionTest {

    @Test
    void shouldNotProvideDiscountForOneItemBought() {
        BuyTwoForOnePoundPromotion buyOneGetOneFreePromotion = new BuyTwoForOnePoundPromotion("A1");
        List<Item> items = List.of(new Product(new BigDecimal("0.60"), "A1").oneOf());

        List<Item> itemsAfterDiscounts = buyOneGetOneFreePromotion.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

}