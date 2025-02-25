package org.skypro.skyshop.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("ПРОДУКТ НЕ НАЙДЕН");
    }
}
