package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.MessageResponse;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class ArticleCategoryService {

    @Autowired
    ArticleCategoryDao categoryArticleDao;

    @Autowired
    private MessageResponse messageResponse;

    public List<ArticleCategory> getAllCategories(){

        List<ArticleCategory> categories = categoryArticleDao.getAllCategories();

        categories.stream().forEach(category -> {
            if(category.getParentCategoryId() != 0) {
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
    public ArticleCategory createArticleCategory(ArticleCategory articleCategory){
        articleCategory.setLevel(0);
        return categoryArticleDao.createArticleCategory(articleCategory);
    }

    /**
     * Remove ArticleCategory
     *
     * @param articleCategory
     * @return
     */
    public MessageResponse removeArticleCategory(ArticleCategory articleCategory){

        articleCategory = categoryArticleDao.removeArticleCategory(articleCategory);

        if(articleCategory.isDeleted()){
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
}
