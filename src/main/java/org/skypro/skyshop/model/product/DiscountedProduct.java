package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    public double basePrice;
    public int discount;

    public DiscountedProduct(UUID id, String name, double basePrice, int discount) {
        super(id, name);
        if (basePrice < 1)
            throw new IllegalArgumentException("Ошибка - цена меньше одного рубля");
        this.basePrice = basePrice;
        if ((discount < 0) || (discount > 100))
            throw new IllegalArgumentException("Ошибка - скидка меньше ноля или больше ста процентов");
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return basePrice * (1 - discount / 100.0);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "< " + getName() + " > " + " < " + getPrice() + " > " + " < " + discount + "% >";
    }

}


