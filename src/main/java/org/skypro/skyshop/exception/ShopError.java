package org.skypro.skyshop.exception;

public record ShopError(String code, String message) {

    @Override
    public String toString() {
        return "ShopError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
