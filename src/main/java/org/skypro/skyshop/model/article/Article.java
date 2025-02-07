package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleTitle;
    private final String articleText;
    private final UUID id;

    public Article(UUID id, String articleTitle, String articleText) {
        this.articleTitle = articleTitle;
        this.articleText = articleText;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Название статьи: " + articleTitle + "; " + "Текст статьи: " + articleText + "; ";
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return this + " Тип - " + getType();
    }

    @JsonIgnore
    @Override
    public String getType() {
        return " ARTICLE ";
    }

    @Override
    public String getStringRepresentation() {
        return "№ id - " + getId() + "; " + getType() + "; Название статьи: " + articleTitle + "; " + " Текст статьи: " + articleText;
    }

    @Override
    public String getName() {
        return getSearchTerm();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleText, article.articleText) && Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleTitle, articleText);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
