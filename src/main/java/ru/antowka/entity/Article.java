package ru.antowka.entity;

import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
public class Article {

    private int articleId;
    private User userOwner;
    private String title;
    private String shortDescription;
    private String description;
    private String creationDate;
    private Set<ArticleCategory> categories;
    private boolean isDeleted = false;

    /**
     * Method response entity name
     *
     * @return
     */
    public String getEntityName() {
        return "Article";
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ArticleCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ArticleCategory> categories) {
        this.categories = categories;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
