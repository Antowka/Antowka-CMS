package ru.antowka.entity;

/**
 * Created by anton on 03.08.15.
 */
public class CategoryArticle {

    private int categoryArticleId;
    private int parentCategoryId;
    private String title;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryArticleId() {
        return categoryArticleId;
    }

    public void setCategoryArticleId(int categoryArticleId) {
        this.categoryArticleId = categoryArticleId;
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
}
