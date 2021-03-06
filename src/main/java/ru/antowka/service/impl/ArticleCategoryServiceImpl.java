package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.MessageResponse;
import ru.antowka.service.ArticleCategoryService;
import ru.antowka.service.MessageResponseService;

import java.util.List;

/**
 * Created by Anton Nikanorov on 06.08.15.
 */
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    /**
     * Link on ArticleCategoryDao use for working with DB
     */
    @Autowired
    ArticleCategoryDao articleCategoryDao;

    /**
     * Link on MessageResponseService use for create response messages
     */
    @Autowired
    private MessageResponseService messageResponseService;

    /**
     * Method - response all categories in tree structure
     *
     * @return List<ArticleCategory>
     */
    @Override
    public List<ArticleCategory> getAllCategories(){

        List<ArticleCategory> categories = articleCategoryDao.getAllCategories();

        //iterate categories for create tree structure by level
        categories.stream().forEach(category -> {

            if(category.getParentCategoryId() != null) {

                categories.stream().forEach(categoryParent -> {

                    if (category.getParentCategoryId() == categoryParent.getArticleCategoryId()) {
                        categoryParent.addChildArticleCategories(category);
                    }
                });
            }
        });

        //remove all categories from main level except level == 0
        categories.removeIf(regionRemove -> regionRemove.getLevel() != 0);

        return categories;
    }

    /**
     * Method - response one category by his articleCategoryId
     *
     * @param articleCategoryId
     * @return ArticleCategory
     */
    @Override
    public ArticleCategory getArticlesByCategoryId(int articleCategoryId){

        return articleCategoryDao.getCategoryById(articleCategoryId);
    }

    /**
     * Method create new ArticleCategory by object ArticleCategory
     *
     * @param articleCategory
     *
     * @return ArticleCategory
     */
    @Override
    public ArticleCategory createArticleCategory(ArticleCategory articleCategory) {

        articleCategory = getLevelForArticleCategory(articleCategory);

        return articleCategoryDao.createArticleCategory(articleCategory);
    }

    /**
     * Method update category by object ArticleCategory
     *
     * @param articleCategory
     *
     * @return ArticleCategory
     */
    @Override
    public ArticleCategory updateArticleCategory(ArticleCategory articleCategory) {

        articleCategory = getLevelForArticleCategory(articleCategory);

        return articleCategoryDao.updateArticleCategory(articleCategory);
    }

    /**
     * Remove ArticleCategory by object ArticleCategory, method response MessageResponse with status success/fail
     *
     * @param articleCategory
     *
     * @return MessageResponse
     */
    @Override
    public MessageResponse removeArticleCategory(ArticleCategory articleCategory) {

        articleCategory = articleCategoryDao.removeArticleCategory(articleCategory);

        return messageResponseService.getResponseForRemoveEntity(
                articleCategory.isDeleted(),
                articleCategory.getEntityName(),
                articleCategory.getArticleCategoryId()
        );
    }

    /**
     * Method get level for ArticleCategory by found parentCategory
     *
     * @param articleCategory
     * @return
     */
    private ArticleCategory getLevelForArticleCategory(ArticleCategory articleCategory) {

        try {

            if(articleCategory.getParentCategoryId() != null) {

                ArticleCategory parentCategory = articleCategoryDao.getCategoryById(articleCategory.getParentCategoryId());
                articleCategory.setLevel(parentCategory.getLevel() + 1);

            } else {

                articleCategory.setLevel(0);
            }

        }catch(Exception e){
            e.getStackTrace();
        }

        return articleCategory;
    }


    /**
     *  ******************************* Getters and Setters ***********************************
     */

    public ArticleCategoryDao getArticleCategoryDao() {
        return articleCategoryDao;
    }

    public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
        this.articleCategoryDao = articleCategoryDao;
    }

    public MessageResponseService getMessageResponseService() {
        return messageResponseService;
    }

    public void setMessageResponseService(MessageResponseService messageResponseService) {
        this.messageResponseService = messageResponseService;
    }
}
