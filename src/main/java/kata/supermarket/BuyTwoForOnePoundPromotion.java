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
        int numOfItems = itemsEligibleForDiscount.size();
        if (numOfItems < 2) {
            return items;
        }
        BigDecimal discount = (itemsEligibleForDiscount.get(0).price().add(itemsEligibleForDiscount.get(1).price()).subtract(BigDecimal.ONE)).divide(new BigDecimal("2.00"), RoundingMode.HALF_UP);
        int numOfItemsNotToBeDiscounted = numOfItems % 2;
        int itemsToBeDiscounted = numOfItems - numOfItemsNotToBeDiscounted;
        List<Item> itemsWithDiscounts = itemsEligibleForDiscount.stream().limit(itemsToBeDiscounted).map(i -> i.withDiscount(discount)).collect(Collectors.toList());
        List<Item> itemsWithoutDiscounts = itemsEligibleForDiscount.stream().skip(itemsWithDiscounts.size()).collect(Collectors.toList());
        List<Item> itemsEligibleForDiscountsAfterDiscounts = Stream.concat(itemsWithDiscounts.stream(), itemsWithoutDiscounts.stream()).collect(Collectors.toList());
        return Stream.concat(itemsEligibleForDiscountsAfterDiscounts.stream(), itemsEligibleAndNonEligibleForDiscount.get(false).stream()).collect(Collectors.toList());
    }
}
