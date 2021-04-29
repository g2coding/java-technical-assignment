package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;
    private final BigDecimal discount;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this(product, weightInKilos, BigDecimal.ZERO);
    }

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos, BigDecimal discount) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discount = discount;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getWeightInKilos() {
        return weightInKilos;
    }

    @Override
    public BigDecimal discount() {
        return discount;
    }

    @Override
    public Item withDiscount(BigDecimal discount) {
        return new ItemByWeight(product, weightInKilos, discount);
    }

    @Override
    public String getProductCode() {
        return product.getProductCode();
    }
}
