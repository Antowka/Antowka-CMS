package ru.antowka.entity;

import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
public class ArticleCategory {

    private int articleCategoryId;
    private int parentCategoryId;
    private String title;
    private String description;
    private Set<Article> articles;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(int articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
