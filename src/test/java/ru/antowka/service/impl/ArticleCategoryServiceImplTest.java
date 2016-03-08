package ru.antowka.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.dao.Impl.ArticleCategoryDaoImpl;
import ru.antowka.entity.ArticleCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Nikanorov on 07.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleCategoryServiceImplTest {

    @InjectMocks
    private ArticleCategoryServiceImpl articleCategoryService;

    @Mock
    private ArticleCategoryDaoImpl articleCategoryDao;

    /**
     * Check build tree by level field from entity
     *
     * @throws Exception
     */
    @Test
    public void testGetAllCategories() throws Exception {

        List<ArticleCategory> testCategories = new ArrayList<>();

        //Create first demo category
        testCategories.add(builderArticleCategory(1, 0, 0, "Main Category"));

        //Create second demo category
        testCategories.add(builderArticleCategory(2, 1, 1, "Main Category"));

        //Create third demo category
        testCategories.add(builderArticleCategory(3, 2, 2, "Main Category"));

        //Create second main demo category
        testCategories.add(builderArticleCategory(4, 0, 0, "Main Category"));


        //Return demo from MockDB
        Mockito.when(articleCategoryDao.getAllCategories()).thenReturn(testCategories);

        List<ArticleCategory> categories = articleCategoryService.getAllCategories();

        int articleCategoryThirdId = categories.get(0).getChildArticleCategories()
                                               .get(0).getChildArticleCategories()
                                               .get(0).getArticleCategoryId();

        int articleCategorySecondMainId = categories.get(1).getArticleCategoryId();

        Assert.assertTrue(articleCategoryThirdId == 3 && articleCategorySecondMainId == 4);
    }

    @Test
    public void testGetArticlesByCategoryId() throws Exception {

        Mockito.when(articleCategoryDao.getCategoryById(1)).thenReturn(builderArticleCategory(1, 0, 0, "Main Category"));

        ArticleCategory articleCategory = articleCategoryService.getArticlesByCategoryId(1);

        Assert.assertTrue(articleCategory.getArticleCategoryId() == 1);
    }

    @Test
    public void testCreateArticleCategory() throws Exception {

        ArticleCategory articleCategoryParent   = builderArticleCategory(1, 2, 1, "Parent Category");
        ArticleCategory articleCategoryNew      = builderArticleCategory(0, 1, 0, "New Category");

        Mockito.when(articleCategoryDao.getCategoryById(1)).thenReturn(articleCategoryParent);
        Mockito.when(articleCategoryDao.createArticleCategory(articleCategoryNew)).thenReturn(articleCategoryNew);

        articleCategoryService.createArticleCategory(articleCategoryNew);

        ArgumentCaptor<ArticleCategory> argument = ArgumentCaptor.forClass(ArticleCategory.class);
        Mockito.verify(articleCategoryDao, Mockito.times(1)).createArticleCategory(argument.capture());

        Assert.assertEquals(2, argument.getValue().getLevel());
    }

    @Test
    public void testUpdateArticleCategory() throws Exception {

    }

    @Test
    public void testRemoveArticleCategory() throws Exception {

    }

    /**
     * Build object ArticleCategory by demo params
     *
     * @param articleCategoryId
     * @param parentArticleCategoryId
     * @param level
     * @param title
     * @return
     */
    private ArticleCategory builderArticleCategory(int articleCategoryId, int parentArticleCategoryId, int level, String title){

        ArticleCategory articleCategory = new ArticleCategory();

        if(articleCategoryId != 0) {
            articleCategory.setArticleCategoryId(articleCategoryId);
        }

        if(parentArticleCategoryId != 0) {
            articleCategory.setParentCategoryId(parentArticleCategoryId);
        }

        articleCategory.setLevel(level);
        articleCategory.setTitle(title);
        articleCategory.setDescription("Description Category");

        return articleCategory;
    }
}