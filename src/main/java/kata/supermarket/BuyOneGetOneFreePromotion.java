package kata.supermarket;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BuyOneGetOneFreePromotion extends Promotion{

    public BuyOneGetOneFreePromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        return items.stream().map(i -> i.withDiscount(BigDecimal.ZERO)).collect(Collectors.toList());
    }
}
