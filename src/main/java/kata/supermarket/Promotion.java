package kata.supermarket;

import java.util.*;

public abstract class Promotion {

    //The code of the product to which the promotion applies
    private String productCode;

    public Promotion(String productCode) {
        this.productCode = productCode;
    }

    public abstract List<Item> apply(List<Item> items);

    public String getProductCode() {
        return productCode;
    }
}
