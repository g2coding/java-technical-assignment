package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final String productCode;

    public WeighedProduct(final BigDecimal pricePerKilo, String productCode) {
        this.pricePerKilo = pricePerKilo;
        this.productCode = productCode;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    public String getProductCode() {
        return productCode;
    }
}
