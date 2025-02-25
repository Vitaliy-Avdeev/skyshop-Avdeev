package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> availableProducts;
    private final Map<UUID, Article> availableArticle;

    public StorageService(Map<UUID, Product> availableProducts, Map<UUID, Article> availableArticle) {
        this.availableProducts = availableProducts;
        this.availableArticle = availableArticle;

        implementationOfTestData();
        System.out.println(availableProducts.values());
    }

    public Map<UUID, Product> getProduct() {
        return this.availableProducts;
    }

    public Map<UUID, Article> getArticle() {
        return this.availableArticle;
    }

    private void implementationOfTestData() {
        SimpleProduct product1 = new SimpleProduct(UUID.randomUUID(), "Коктель", (double) 80.3F);
        SimpleProduct product2 = new SimpleProduct(UUID.randomUUID(), "Роллы", (double) 560.8F);
        DiscountedProduct product3 = new DiscountedProduct(UUID.randomUUID(), "Пицца", (double) 658.1F, 20);
        DiscountedProduct product4 = new DiscountedProduct(UUID.randomUUID(), "Картофель", (double) 278.7F, 18);
        FixPriceProduct product5 = new FixPriceProduct(UUID.randomUUID(), "Кола");
        SimpleProduct product6 = new SimpleProduct(UUID.randomUUID(), "Напиток", (double) 45.03F);

        Article article1 = new Article(UUID.randomUUID(), "1", "1");
        Article article2 = new Article(UUID.randomUUID(), "2", "2");
        Article article3 = new Article(UUID.randomUUID(), "33", "33");
        Article article4 = new Article(UUID.randomUUID(), "4", "4");

        availableProducts.put(product1.getId(), product1);
        availableProducts.put(product2.getId(), product2);
        availableProducts.put(product3.getId(), product3);
        availableProducts.put(product4.getId(), product4);
        availableProducts.put(product5.getId(), product5);
        availableProducts.put(product6.getId(), product6);

        availableArticle.put(article1.getId(), article1);
        availableArticle.put(article2.getId(), article2);
        availableArticle.put(article3.getId(), article3);
        availableArticle.put(article4.getId(), article4);
    }

    public List<Searchable> getSearchable() {
        List<Searchable> searchable = new ArrayList<>();
        searchable.addAll(availableProducts.values());
        searchable.addAll(availableArticle.values());
        return searchable;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));

    }
}




