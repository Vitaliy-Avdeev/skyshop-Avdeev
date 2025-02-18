package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Map<UUID, SearchResult> search(String pattern) {
        return storageService.getSearchables().entrySet().stream().filter(entry -> entry.getValue().
                getSearchTerm().contains(pattern)).collect(Collectors.toMap(Map.Entry::getKey, entry ->
                new SearchResult(entry.getValue().getId(), entry.getValue().getName(), entry.getValue().getType())));


    }
}
