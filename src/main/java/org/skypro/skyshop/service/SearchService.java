package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;


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
        Map<UUID, Searchable> searchables = storageService.getSearchables();
        TreeSet<Map.Entry<UUID, Searchable>> searchResults;
        searchResults = searchables.entrySet().stream().filter(searchable -> searchable.getValue().getSearchTerm().contains(pattern))
                .collect(Collectors.toCollection(TreeSet::new));
        return (Map<UUID, SearchResult>) searchResults;
    }
}
