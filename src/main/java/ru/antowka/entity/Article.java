package ru.antowka.entity;

/**
 * Created by anton on 03.08.15.
 */
public class Article {

    private int articleId;
    private int userOwnerid;
    private String title;
    private String shortDescripion;
    private String description;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserOwnerid() {
        return userOwnerid;
    }

    public void setUserOwnerid(int userOwnerid) {
        this.userOwnerid = userOwnerid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescripion() {
        return shortDescripion;
    }

    public void setShortDescripion(String shortDescripion) {
        this.shortDescripion = shortDescripion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
