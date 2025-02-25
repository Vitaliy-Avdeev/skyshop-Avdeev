package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.ShopError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ShopControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ShopError("404", "ПРОДУКТ НЕ НАЙДЕН"));
    }
}