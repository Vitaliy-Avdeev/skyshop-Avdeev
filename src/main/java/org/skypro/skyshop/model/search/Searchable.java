package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    UUID getId();

    String getSearchTerm();

    String getType();

    String getName();

    default String getStringRepresentation() {
        return "№ id " + getId() + "; Тип объекта: - " + getType() + " ;" + " Название объекта: - " + getName() + "; " + getSearchTerm();


    }
}