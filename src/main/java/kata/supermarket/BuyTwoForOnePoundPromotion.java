package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuyTwoForOnePoundPromotion extends Promotion {

    public BuyTwoForOnePoundPromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        Map<Boolean, List<Item>> itemsEligibleAndNonEligibleForDiscount = items.stream()
            .collect(Collectors.partitioningBy(i -> i.getProductCode().equals(getProductCode())));

        List<Item> itemsEligibleForDiscount = itemsEligibleAndNonEligibleForDiscount.get(true);
        int numOfEligibleItems = itemsEligibleForDiscount.size();
        if (numOfEligibleItems < 2) {
            return items;
        }
        List<Item> itemsEligibleForDiscountsAfterDiscounts =
            getItemsEligibleForDiscountsAfterDiscounts(itemsEligibleForDiscount, numOfEligibleItems);
        return Stream.concat(itemsEligibleForDiscountsAfterDiscounts.stream(), itemsEligibleAndNonEligibleForDiscount.get(false).stream()).collect(Collectors.toList());
    }

    private List<Item> getItemsEligibleForDiscountsAfterDiscounts(
        List<Item> itemsEligibleForDiscount,
        int numOfEligibleItems
    ) {
        //I assume products with the same product code have the same price.
        BigDecimal
            discount = itemsEligibleForDiscount.get(0).price().multiply(new BigDecimal("2")).subtract(BigDecimal.ONE).divide(new BigDecimal("2.00"), RoundingMode.HALF_UP);
        int numOfItemsNotToBeDiscounted = numOfEligibleItems % 2;
        int itemsToBeDiscounted = numOfEligibleItems - numOfItemsNotToBeDiscounted;
        List<Item> itemsWithDiscounts = itemsEligibleForDiscount.stream().limit(itemsToBeDiscounted).map(i -> i.withDiscount(discount)).collect(
            Collectors.toList());
        List<Item> itemsWithoutDiscounts = itemsEligibleForDiscount.stream().skip(itemsWithDiscounts.size()).collect(Collectors.toList());
        return Stream.concat(itemsWithDiscounts.stream(), itemsWithoutDiscounts.stream()).collect(Collectors.toList());
    }
}
