package ru.antowka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Anton Nik on 03.08.15.
 */
@Entity
public class ArticleCategory {

    private int articleCategoryId;
    private int parentCategoryId;
    private String title;
    private String description;
    private int level;
    private List<ArticleCategory> childArticleCategories = new ArrayList<ArticleCategory>();

    @JsonIgnore
    private Set<Article> articles;

    private boolean isDeleted = false;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<ArticleCategory> getChildArticleCategories() {
        return childArticleCategories;
    }

    public void setChildArticleCategories(List<ArticleCategory> childArticleCategories) {
        this.childArticleCategories = childArticleCategories;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Method for create tree with child elements
     *
     * @param articleCategory
     */
    public void addChildArticleCategories(ArticleCategory articleCategory){
        childArticleCategories.add(articleCategory);
    }
}
