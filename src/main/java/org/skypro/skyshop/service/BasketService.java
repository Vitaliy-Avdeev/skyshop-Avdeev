package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Collectors;

@Service
@SessionScope
public class BasketService {
    private final Map<UUID, Integer> basket;
    private final StorageService storageService;

    public BasketService(Map<UUID, Integer> basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isEmpty()) throw new NoSuchProductException();
        basket.merge(id, 1, Integer::sum);
    }

    public UserBasket getUserBasket() {

        List<BasketItem> basketItems = basket.entrySet().stream().map(entry -> {
            Product product = storageService.getProductById(entry.getKey()).orElseThrow(NoSuchProductException::new);
            return new BasketItem(product, entry.getValue());
        }).collect(Collectors.<BasketItem>toList());

        return new UserBasket((ArrayList<BasketItem>) basketItems);


    }
}

