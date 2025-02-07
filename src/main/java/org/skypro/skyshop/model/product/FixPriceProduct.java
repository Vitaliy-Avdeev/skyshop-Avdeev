package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final double FIX_PRICE = 200;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public double getPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "< " + getName() + " > : Фиксированная цена < " + FIX_PRICE + " > ";
    }

}

