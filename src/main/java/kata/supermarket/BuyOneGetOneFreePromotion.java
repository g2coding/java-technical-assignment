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
        int freeItems = items.size() / 2;
        Stream<Item> freeProducts = items.stream().limit(freeItems).map(i -> i.withDiscount(i.price()));
        Stream<Item> productsToPay = items.stream().skip(freeItems);
        return Stream.concat(freeProducts, productsToPay).collect(Collectors.toList());
    }
}
