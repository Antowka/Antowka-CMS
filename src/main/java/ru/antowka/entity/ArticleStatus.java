package ru.antowka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Anton Nik on 10.08.15.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticleStatus implements Serializable{

    private int articlesStatusId;
    private String status;
    private boolean publicStatus;

    @JsonIgnore
    private Set<Article> articles;

    public int getArticlesStatusId() {
        return articlesStatusId;
    }

    public void setArticlesStatusId(int articlesStatusId) {
        this.articlesStatusId = articlesStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
