package ru.antowka.entity;

/**
 * Created by anton on 03.08.15.
 */
public class Article {

    private int articleId;
    private String title;
    private String short_descripion;
    private String description;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_descripion() {
        return short_descripion;
    }

    public void setShort_descripion(String short_descripion) {
        this.short_descripion = short_descripion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    private boolean isSystem;
    private boolean isDelete;


}
