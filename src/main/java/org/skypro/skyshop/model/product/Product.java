package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id ;
    protected String name;

    public Product(UUID id, String name) {
        if ((name == null) || (name.isBlank())) {
            throw new IllegalArgumentException("Ошибка - не введен продукт");
        }
        this.name = name;
        this.id = id;
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName() + " - " + getType() + " - " + getId();
    }
    @JsonIgnore
    @Override
    public String getType() {
        return " PRODUCT ";
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();

    public abstract boolean isSpecial();

    public abstract String toString();
    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

