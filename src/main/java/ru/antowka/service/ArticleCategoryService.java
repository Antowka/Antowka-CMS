package ru.antowka.service;

import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.MessageResponse;

import java.util.List;

/**
 * Created by Anton Nik on 24.12.15.
 */
public interface ArticleCategoryService {

    /**
     * Method - response all categories in tree structure
     *
     * @return List<ArticleCategory>
     */
    List<ArticleCategory> getAllCategories();

    /**
     * Method - response one category by his articleCategoryId
     *
     * @param articleCategoryId
     * @return ArticleCategory
     */
    ArticleCategory getArticlesByCategoryId(int articleCategoryId);

    /**
     * Method create new ArticleCategory by object ArticleCategory
     *
     * @param articleCategory
     *
     * @return ArticleCategory
     */
    ArticleCategory createArticleCategory(ArticleCategory articleCategory);

    /**
     * Method update category by object ArticleCategory
     *
     * @param articleCategory
     *
     * @return ArticleCategory
     */
    ArticleCategory updateArticleCategory(ArticleCategory articleCategory);

    /**
     * Remove ArticleCategory by object ArticleCategory, method response MessageResponse with status success/fail
     *
     * @param articleCategory
     *
     * @return MessageResponse
     */
    MessageResponse removeArticleCategory(ArticleCategory articleCategory);
}
