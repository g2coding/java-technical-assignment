package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyTwoForOnePoundPromotionTest {

    @Test
    void shouldNotProvideDiscountForOneItemBought() {
        BuyTwoForOnePoundPromotion buyTwoForOnePoundPromotion = new BuyTwoForOnePoundPromotion("A1");
        List<Item> items = List.of(new Product(new BigDecimal("0.60"), "A1").oneOf());

        List<Item> itemsAfterDiscounts = buyTwoForOnePoundPromotion.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

    @Test
    void shouldProvideDiscountForTwoItemsBought() {
        BuyTwoForOnePoundPromotion buyTwoForOnePoundPromotion = new BuyTwoForOnePoundPromotion("A1");
        Item crisps = new Product(new BigDecimal("0.60"), "A1").oneOf();
        List<Item> items = List.of(crisps, crisps);
        BigDecimal expectedDiscount = new BigDecimal("0.10");

        List<Item> itemsAfterDiscounts = buyTwoForOnePoundPromotion.apply(items);

        assertEquals(expectedDiscount, itemsAfterDiscounts.get(0).discount());
        assertEquals(expectedDiscount, itemsAfterDiscounts.get(1).discount());
    }

    @Test
    @DisplayName("Should provide discount only for eligible items bought even if there are more items with different code")
    void shouldProvideDiscountOnlyForEligibleItemsBoughtEvenIfThereAreMoreItemsWithDifferentCode() {
        BuyTwoForOnePoundPromotion buyTwoForOnePoundPromotion = new BuyTwoForOnePoundPromotion("A1");
        Item crisps = new Product(new BigDecimal("0.60"), "A1").oneOf();
        Item yoghurt = new Product(new BigDecimal("0.50"), "B").oneOf();

        List<Item> items = List.of(crisps, crisps, crisps, yoghurt, yoghurt);
        BigDecimal expectedDiscount = new BigDecimal("0.10");

        List<Item> itemsAfterDiscounts = buyTwoForOnePoundPromotion.apply(items);

        assertEquals(expectedDiscount, itemsAfterDiscounts.get(0).discount());
        assertEquals(expectedDiscount, itemsAfterDiscounts.get(1).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(2).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(3).discount());
    }

}