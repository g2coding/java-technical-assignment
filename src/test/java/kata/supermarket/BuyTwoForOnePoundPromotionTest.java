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

        assertEquals(1, itemsAfterDiscounts.size());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

    @Test
    void shouldProvideDiscountForTwoItemsBought() {
        BuyTwoForOnePoundPromotion buyTwoForOnePoundPromotion = new BuyTwoForOnePoundPromotion("A1");
        Item crisps = new Product(new BigDecimal("0.60"), "A1").oneOf();
        List<Item> items = List.of(crisps, crisps);
        BigDecimal expectedDiscount = new BigDecimal("0.10");

        List<Item> itemsAfterDiscounts = buyTwoForOnePoundPromotion.apply(items);

        assertEquals(2, itemsAfterDiscounts.size());
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

        long actualItemsWithDiscount = getNumOfFilteredItems(crisps, expectedDiscount, itemsAfterDiscounts);
        long actualCrispsItemsWithoutDiscount = getNumOfFilteredItems(crisps, BigDecimal.ZERO, itemsAfterDiscounts);
        long actualYoghurtItemsWithoutDiscount = getNumOfFilteredItems(yoghurt, BigDecimal.ZERO, itemsAfterDiscounts);

        assertEquals(5, itemsAfterDiscounts.size());
        assertEquals(2, actualItemsWithDiscount);
        assertEquals(1, actualCrispsItemsWithoutDiscount);
        assertEquals(2, actualYoghurtItemsWithoutDiscount);
    }

    private long getNumOfFilteredItems(Item crisps, BigDecimal expectedDiscount, List<Item> itemsAfterDiscounts) {
        return itemsAfterDiscounts
            .stream()
            .filter(i -> i.discount().equals(expectedDiscount))
            .filter(i -> i.getProductCode().equals(crisps.getProductCode()))
            .count();
    }
}