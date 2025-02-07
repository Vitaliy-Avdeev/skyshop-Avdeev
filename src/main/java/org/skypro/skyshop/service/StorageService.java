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
    private final Map<UUID, Product> product;
    private final Map<UUID, Article> article;

    public StorageService(Map<UUID, Product> product, Map<UUID, Article> article) {
        this.product = product;
        this.article = article;

        implementationOfTestData();
        System.out.println(product.values());
    }

    public Map<UUID, Product> getProduct() {
        return this.product;
    }

    public Map<UUID, Article> getArticle() {
        return this.article;
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

        product.put(product1.getId(), product1);
        product.put(product2.getId(), product2);
        product.put(product3.getId(), product3);
        product.put(product4.getId(), product4);
        product.put(product5.getId(), product5);
        product.put(product6.getId(), product6);

        article.put(article1.getId(), article1);
        article.put(article2.getId(), article2);
        article.put(article3.getId(), article3);
        article.put(article4.getId(), article4);

    }

    public Map<UUID, Searchable> getSearchables() {
        Map<UUID, Searchable> searchables = new HashMap<>();
        int i = 0;
        for (Product product : product.values()) {
            searchables.put(product.getId(), product);
            i++;
        }
        for (Article article : article.values()) {
            searchables.put(article.getId(), article);
            i++;
        }
        return searchables;


    }
}



