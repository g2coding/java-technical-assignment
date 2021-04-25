package kata.supermarket;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuyOneGetOneFreePromotion extends Promotion{

    public BuyOneGetOneFreePromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        List<Item> itemsWithApplicableProductCode =
            items.stream().filter(i -> i.getProductCode().equals(getProductCode())).collect(Collectors.toList());
        if (itemsWithApplicableProductCode.isEmpty()) {
            return items;
        }
        int freeItems = itemsWithApplicableProductCode.size() / 2;
        Stream<Item> freeProducts = itemsWithApplicableProductCode.stream().limit(freeItems).map(i -> i.withDiscount(i.price()));
        Stream<Item> productsToPay = itemsWithApplicableProductCode.stream().skip(freeItems);
        return Stream.concat(freeProducts, productsToPay).collect(Collectors.toList());
    }
}
