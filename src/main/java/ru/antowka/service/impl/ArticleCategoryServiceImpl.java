package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.MessageResponse;
import ru.antowka.service.ArticleCategoryService;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    ArticleCategoryDao categoryArticleDao;

    @Autowired
    private MessageResponse messageResponse;

    @Override
    public List<ArticleCategory> getAllCategories(){

        List<ArticleCategory> categories = categoryArticleDao.getAllCategories();

        categories.stream().forEach(category -> {
            if(category.getParentCategoryId() != null) {
                categories.stream().forEach(categoryParent -> {
                    if (category.getParentCategoryId() == categoryParent.getArticleCategoryId()) {
                        categoryParent.addChildArticleCategories(category);
                    }
                });
            }
        });

        categories.removeIf(regionRemove -> regionRemove.getLevel() != 0);

        return categories;
    }

    @Override
    public ArticleCategory getArticlesByCategoryId(int categoryId){

        ArticleCategory category = null;
        category = categoryArticleDao.getCategoryById(categoryId);
        return category;
    }

    /**
     * Method create new ArticleCategory
     *
     * @param articleCategory
     * @return
     */
    @Override
    public ArticleCategory createArticleCategory(ArticleCategory articleCategory) {

        articleCategory = getLevelForArticleCategory(articleCategory);

        return categoryArticleDao.createArticleCategory(articleCategory);
    }

    /**
     * Method update category
     *
     * @param articleCategory
     * @return
     */
    @Override
    public ArticleCategory updateArticleCategory(ArticleCategory articleCategory) {

        articleCategory = getLevelForArticleCategory(articleCategory);

        return categoryArticleDao.updateArticleCategory(articleCategory);
    }

    /**
     * Remove ArticleCategory
     *
     * @param articleCategory
     * @return
     */
    @Override
    public MessageResponse removeArticleCategory(ArticleCategory articleCategory){

        articleCategory = categoryArticleDao.removeArticleCategory(articleCategory);

        if(articleCategory.isDeleted()) {

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful");
            messageResponse.setMessage("Your category #" + articleCategory.getArticleCategoryId() + " removed from system");
        } else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Category has not been removed");
            messageResponse.setMessage("Your category #" + articleCategory.getArticleCategoryId() + " removed to system");
        }

        return messageResponse;
    }

    /**
     * Method get level for ArticleCategory
     *
     * @param articleCategory
     * @return
     */
    private ArticleCategory getLevelForArticleCategory(ArticleCategory articleCategory){

        try {

            if(articleCategory.getParentCategoryId() != null) {
                ArticleCategory parentCategory = categoryArticleDao.getCategoryById(articleCategory.getParentCategoryId());
                articleCategory.setLevel(parentCategory.getLevel() + 1);
            }
        }catch(Exception e){
            e.getStackTrace();
        }

        return articleCategory;
    }
}
