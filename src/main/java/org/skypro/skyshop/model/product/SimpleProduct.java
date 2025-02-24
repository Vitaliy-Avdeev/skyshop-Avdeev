package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final double price;

    public SimpleProduct(UUID id, String name, double price) {
        super(id, name);
        if (price < 1)
            throw new IllegalArgumentException("Ошибка - цена меньше одного");
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "< " + getName() + " > " + " < " + getPrice() + " >";
    }

}


