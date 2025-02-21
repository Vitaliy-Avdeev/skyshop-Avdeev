package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }
    public List<SearchResult> search(String pattern) {
        return storageService.getSearchable().stream()
                .filter(entry -> entry.getSearchTerm().contains(pattern))
                .map(SearchResult::fromSearchable).toList();

    }
}
