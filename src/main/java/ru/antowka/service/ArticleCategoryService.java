package ru.antowka.service;

import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.MessageResponse;

import java.util.List;

/**
 * Created by Anton Nik on 24.12.15.
 */
public interface ArticleCategoryService {

    List<ArticleCategory> getAllCategories();

    ArticleCategory getArticlesByCategoryId(int categoryId);

    ArticleCategory createArticleCategory(ArticleCategory articleCategory);

    ArticleCategory updateArticleCategory(ArticleCategory articleCategory);

    MessageResponse removeArticleCategory(ArticleCategory articleCategory);
}
