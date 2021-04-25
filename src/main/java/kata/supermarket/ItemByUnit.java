package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private BigDecimal discount;

    ItemByUnit(final Product product) {
        this(product, BigDecimal.ZERO);
    }

    ItemByUnit(final Product product, BigDecimal discount) {
        this.product = product;
        this.discount = discount;
    }

    @Override
    public ItemByUnit withDiscount(BigDecimal discount){
        return new ItemByUnit(product, discount);
    }

    @Override
    public String getProductCode() {
        return product.getProductCode();
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    public BigDecimal discount() {
        return discount;
    }
}
