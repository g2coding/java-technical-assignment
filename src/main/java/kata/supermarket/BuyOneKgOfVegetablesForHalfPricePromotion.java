package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuyOneKgOfVegetablesForHalfPricePromotion extends Promotion {

    public BuyOneKgOfVegetablesForHalfPricePromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        Map<Boolean, List<Item>> itemsEligibleAndNonEligibleForDiscount = items.stream()
            .collect(Collectors.partitioningBy(i -> i.getProductCode().startsWith(getProductCode())));

        List<Item> weighedItemsEligibleForDiscount = getWeightedItemsEligibleForDiscount(itemsEligibleAndNonEligibleForDiscount.get(true));
        validateItemsAreWeight(itemsEligibleAndNonEligibleForDiscount, weighedItemsEligibleForDiscount);
        if (weighedItemsEligibleForDiscount.isEmpty()) {
            return items;
        }
        BigDecimal totalEligibleWeight = calculateTotalEligibleWeight(weighedItemsEligibleForDiscount);
        if (totalEligibleWeight.compareTo(BigDecimal.ONE) < 0) {
            return items;
        }
        List<Item> itemsAfterDiscounts = weighedItemsEligibleForDiscount.stream().map(i -> i.withDiscount(i.price().divide(new BigDecimal("2"), RoundingMode.HALF_UP))).collect(
            Collectors.toList());
        return Stream.concat(itemsAfterDiscounts.stream(), itemsEligibleAndNonEligibleForDiscount.get(false).stream()).collect(
            Collectors.toList());
    }

    private void validateItemsAreWeight(Map<Boolean, List<Item>> itemsEligibleAndNonEligibleForDiscount, List<Item> weighedItemsEligibleForDiscount) {
        if (weighedItemsEligibleForDiscount.size() != itemsEligibleAndNonEligibleForDiscount.get(true).size()) {
            throw new RuntimeException("Non weighed items in the given product category");
        }
    }

    private BigDecimal calculateTotalEligibleWeight(List<Item> weighedItemsEligibleForDiscount) {
        return weighedItemsEligibleForDiscount.stream()
            .map(ItemByWeight.class::cast)
            .map(ItemByWeight::getWeightInKilos)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Item> getWeightedItemsEligibleForDiscount(List<Item> items) {
        return items.stream()
            //group of products e.g. code starting with 'V' - V123, V456 etc
            .filter(ItemByWeight.class::isInstance)
            .map(ItemByWeight.class::cast)
            .collect(Collectors.toList());
    }
}
