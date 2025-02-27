package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private Map<UUID, Integer> basket;
    @InjectMocks
    private BasketService basketService;

    @Test
    void testForThrowingAnExceptionWhenAddingNonexistentObjectToTheCart() {
        UUID nonExistentProductId = UUID.randomUUID();

        when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(nonExistentProductId),
                " Исключение - NoSuchProductException ");
    }

    @Test
    void testForAnExistingProductThatCallsTheMethodOfAddingProductToMockProductCart() {
        UUID productId = UUID.randomUUID();

        Product product = new SimpleProduct(productId, "Product", 80.3F);

        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProduct(productId);

        basket.merge(productId, 1, Integer::sum);
    }

    @Test
    void testGetUserBasketReturnsEmptyBasket() {
        when(basket.entrySet()).thenReturn(Collections.emptySet());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getBasket().isEmpty(), "пусто");
    }

    @Test
    void testTheReturnOfMatchingUserCartIfItContainsProducts() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID productId6 = UUID.randomUUID();

        Product product1 = new SimpleProduct(productId1, "Product1", 80.3F);
        Product product2 = new SimpleProduct(productId2, "Product2", 560.8F);
        Product product6 = new SimpleProduct(productId6, "Product3", 45.03F);

        when(basket.entrySet()).thenReturn(Map.of(productId1, 1, productId2, 2, productId6, 3).entrySet());
        when(storageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2)).thenReturn(Optional.of(product2));
        when(storageService.getProductById(productId6)).thenReturn(Optional.of(product6));

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(3, userBasket.getBasket().size(), "в корзине должно быть 3 товара");

        assertFalse(userBasket.getBasket().stream().anyMatch(item ->
                        item.product().equals(product1) && item.count() == 3),
                "корзина должна содержать Product1 с количеством 3");
        assertFalse(userBasket.getBasket().stream().anyMatch(item ->
                        item.product().equals(product2) && item.count() == 1),
                "корзина должна содержать Product2 с количеством 3");
        assertFalse(userBasket.getBasket().stream().anyMatch(item ->
                        item.product().equals(product6) && item.count() == 1),
                "корзина должна содержать Product6 с количеством 3");
    }
}

