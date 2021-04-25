package kata.supermarket;

import java.util.List;

public class BuyTwoForOnePoundPromotion extends Promotion {

    public BuyTwoForOnePoundPromotion(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        if (items.size() < 2) {
            return items;
        }
        return null;
    }
}
