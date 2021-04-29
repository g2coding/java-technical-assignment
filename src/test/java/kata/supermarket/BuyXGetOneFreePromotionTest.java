package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyXGetOneFreePromotionTest {

    BuyXGetOneFreePromotion buyXGetOneFreePromotion = new BuyXGetOneFreePromotion("P1", 3);

    @Test
    void shouldNotProvideDiscountForOneItemBought() {
        List<Item> items = List.of(new Product(new BigDecimal("0.49"), "P1").oneOf());

        List<Item> itemsAfterDiscounts = buyXGetOneFreePromotion.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

    @Test
    void shouldNotProvideDiscountForTwoItemsBought() {
        Item yoghurt = new Product(new BigDecimal("0.49"), "P1").oneOf();
        List<Item> items = List.of(yoghurt, yoghurt);

        List<Item> itemsAfterDiscounts = buyXGetOneFreePromotion.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
    }

    @Test
    void shouldGiveOneProductFreeWhenThreeBought() {
        Item yoghurt = new Product(new BigDecimal("0.50"), "P1").oneOf();
        List<Item> items = List.of(yoghurt, yoghurt, yoghurt);

        List<Item> itemsAfterDiscounts = buyXGetOneFreePromotion.apply(items);

        assertEquals(new BigDecimal("0.50"), itemsAfterDiscounts.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(1).discount());
    }

    @Test
    @DisplayName("The promotion should apply only to products with the same product code as the one included in the promotion")
    void buyOneGetOneFreeShouldApplyOnlyToProductsWithTheGivenProductCode() {
        Item yoghurt = new Product(new BigDecimal("0.50"), "A").oneOf();
        List<Item> items = List.of(yoghurt, yoghurt);

        List<Item> itemsAfterDiscounts = buyXGetOneFreePromotion.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(1).discount());
    }

    @Test
    @DisplayName("The promotion should apply only to products with the same product code as the one included in the promotion also when other products with other codes are present")
    void buyOneGetOneFreeShouldApplyOnlyToProductsWithTheGivenProductCodeWhenMoreProductsWithDifferentCodesIncluded() {
        Item yoghurt = new Product(new BigDecimal("0.50"), "A").oneOf();
        Item milk = new Product(new BigDecimal("1.00"), "P1").oneOf();
        List<Item> items = List.of(milk, milk, milk, yoghurt, yoghurt);

        List<Item> itemsAfterDiscounts = buyXGetOneFreePromotion.apply(items);

        assertEquals(new BigDecimal("1.00"), itemsAfterDiscounts.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(1).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(2).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscounts.get(3).discount());
    }

}