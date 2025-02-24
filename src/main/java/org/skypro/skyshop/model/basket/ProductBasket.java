package org.skypro.skyshop.model.basket;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public record ProductBasket(Map<UUID, Integer> basket) {

    public void addProduct(UUID productId) {
        if (basket.containsKey(productId)) {
            basket.put(productId, basket.get(productId) + 1);
        } else {
            basket.put(productId, 1);
        }
    }

    @Override
    public Map<UUID, Integer> basket() {
        return Collections.unmodifiableMap(basket);
    }
}
