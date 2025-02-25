package org.skypro.skyshop.model.basket;

import java.util.ArrayList;

public class UserBasket {
    private final ArrayList<BasketItem> basket;
    private final double total;

    public UserBasket(ArrayList<BasketItem> basket) {
        this.basket = basket;
        this.total = (double) basket.stream().mapToDouble(item -> item.product().getPrice() * item.count()).sum();

    }

    public ArrayList<BasketItem> getBasket() {
        return basket;
    }

    public double getTotal() {
        return total;
    }
}
