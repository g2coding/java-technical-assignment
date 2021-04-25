package kata.supermarket;

import java.util.*;

public class BuyOneGetOneFreePromotion extends Promotion{

    public BuyOneGetOneFreePromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> basket) {
        return null;
    }
}
