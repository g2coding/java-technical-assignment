package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private final String productCode;

    public Product(final BigDecimal pricePerUnit, String productCode) {
        this.pricePerUnit = pricePerUnit;
        this.productCode = productCode;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public String getProductCode() {
        return productCode;
    }
}
