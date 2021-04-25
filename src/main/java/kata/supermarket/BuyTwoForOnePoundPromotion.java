package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuyTwoForOnePoundPromotion extends Promotion {

    public BuyTwoForOnePoundPromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        int numOfItems = items.size();
        if (numOfItems < 2) {
            return items;
        }
        BigDecimal discount = (items.get(0).price().add(items.get(1).price()).subtract(BigDecimal.ONE)).divide(new BigDecimal("2.00"), RoundingMode.HALF_UP);
        int numOfItemsNotToBeDiscounted = numOfItems % 2;
        int itemsToBeDiscounted = numOfItems - numOfItemsNotToBeDiscounted;
        List<Item> itemsWithDiscounts = items.stream().limit(itemsToBeDiscounted).map(i -> i.withDiscount(discount)).collect(Collectors.toList());
        List<Item> itemsWithoutDiscounts = items.stream().skip(itemsWithDiscounts.size()).collect(Collectors.toList());
        return Stream.concat(itemsWithDiscounts.stream(), itemsWithoutDiscounts.stream()).collect(Collectors.toList());
    }
}
