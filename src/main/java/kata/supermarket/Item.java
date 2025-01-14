package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();
    BigDecimal discount();
    Item withDiscount(BigDecimal discount);
    String getProductCode();
}
