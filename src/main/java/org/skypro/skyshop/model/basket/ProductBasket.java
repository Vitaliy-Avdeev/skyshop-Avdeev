package org.skypro.skyshop.model.basket;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@SessionScoped
public final class ProductBasket {
    private final Map<UUID, Integer> basket;

    public ProductBasket(Map<UUID, Integer> basket) {
        this.basket = basket;
    }

    public void addProduct(UUID productId) {
        if (basket.containsKey(productId)) {
            basket.put(productId, basket.get(productId) + 1);
        } else {
            basket.put(productId, 1);
        }
    }

    public Map<UUID, Integer> basket() {
        return Collections.unmodifiableMap(basket);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ProductBasket) obj;
        return Objects.equals(this.basket, that.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basket);
    }

    @Override
    public String toString() {
        return "ProductBasket[" +
                "basket=" + basket + ']';
    }

}
