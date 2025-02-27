package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearchInTheStorageService_IfTheObjectIsMissing() {
        when(storageService.getSearchable()).thenReturn(Collections.emptyList());

        List<SearchResult> results = searchService.search("PRODUCT");

        assertTrue(results.isEmpty(), "Должно быть пусто");
    }

    @Test
    public void testSearchInTheStorageService_IfObjectsExistButThereIsNoSuitableOne() {
        ArrayList<Searchable> searchables = new ArrayList<>();

        searchables.add(new SimpleProduct(UUID.randomUUID(), "TEST1", 80.3F));
        searchables.add(new Article(UUID.randomUUID(), "TEST2", "CONTENT"));

        when(storageService.getSearchable()).thenReturn((searchables));

        List<SearchResult> results = searchService.search("Совпадений нет!");

        assertTrue(results.isEmpty(), "Результат поиска пустой, если нет подходящего объекта");
    }

    @Test
    public void testSearchInTheStorageService_IfSuitableObjectExists() {
        UUID matchingId = UUID.randomUUID();
        ArrayList<Searchable> searchables = new ArrayList<>();

        searchables.add(new SimpleProduct(matchingId, "Соответствует", 80.3F));
        searchables.add(new Article(UUID.randomUUID(), "Другой", "CONTENT"));

        when(storageService.getSearchable()).thenReturn(searchables);

        List<SearchResult> results = searchService.search("Соответствует");

        assertEquals(1, results.size(), "Содержит 1 объект");

        assertFalse(results.isEmpty(), "совпадение должно быть в результате");

    }
}

