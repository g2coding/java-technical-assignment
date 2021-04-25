package kata.supermarket;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuyOneGetOneFreePromotion extends Promotion{

    public BuyOneGetOneFreePromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        Map<Boolean, List<Item>> itemsEligibleAndNonEligibleForDiscount = items.stream()
            .collect(Collectors.partitioningBy(i -> i.getProductCode().equals(getProductCode())));

        List<Item> itemsWithApplicableProductCode = itemsEligibleAndNonEligibleForDiscount.get(true);

        if (itemsWithApplicableProductCode.isEmpty()) {
            return items;
        }
        int freeItems = itemsWithApplicableProductCode.size() / 2;
        Stream<Item> freeProducts = itemsWithApplicableProductCode.stream().limit(freeItems).map(i -> i.withDiscount(i.price()));
        Stream<Item> productsToPay = itemsWithApplicableProductCode.stream().skip(freeItems);
        List<Item> itemsWithApplicableProductCodeAfterPromotions = Stream.concat(freeProducts, productsToPay).collect(Collectors.toList());
        return Stream.concat(itemsWithApplicableProductCodeAfterPromotions.stream(), itemsEligibleAndNonEligibleForDiscount.get(false).stream()).collect(Collectors.toList());
    }
}
