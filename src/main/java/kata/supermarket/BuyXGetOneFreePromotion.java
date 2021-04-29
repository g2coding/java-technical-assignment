package kata.supermarket;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Note: As this class was added after the time suggested I have not removed the existing BuyOneGetOneFreePromotion.
 * The two promotions BuyOneGetOneFreePromotion and BuyThreeForTwoPromotion can be the same as the field variable "quantity" is the determining factor of which of the two promotions to apply.
 * If it is set to 2 then it is the BuyOneGetOneFreePromotion and if it is set to 3 then it is the BuyThreeForTwoPromotion
 */
public class BuyXGetOneFreePromotion extends Promotion {

    private int quantity;

    public BuyXGetOneFreePromotion(String productCode, int quantity) {
        super(productCode);
        this.quantity = quantity;
    }

    @Override
    public List<Item> apply(List<Item> items) {
        Map<Boolean, List<Item>> itemsEligibleAndNonEligibleForDiscount = items.stream()
            .collect(Collectors.partitioningBy(i -> i.getProductCode().equals(getProductCode())));

        List<Item> itemsWithApplicableProductCode = itemsEligibleAndNonEligibleForDiscount.get(true);

        if (itemsWithApplicableProductCode.isEmpty()) {
            return items;
        }
        int freeItems = itemsWithApplicableProductCode.size() / quantity;
        Stream<Item> freeProducts = itemsWithApplicableProductCode.stream().limit(freeItems).map(i -> i.withDiscount(i.price()));
        Stream<Item> productsToPay = itemsWithApplicableProductCode.stream().skip(freeItems);
        List<Item> itemsWithApplicableProductCodeAfterPromotions = Stream.concat(freeProducts, productsToPay).collect(Collectors.toList());
        return Stream.concat(itemsWithApplicableProductCodeAfterPromotions.stream(), itemsEligibleAndNonEligibleForDiscount.get(false).stream()).collect(Collectors.toList());
    }
}
