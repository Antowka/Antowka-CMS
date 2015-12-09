package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.Ticket;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton Nik on 03.08.15.
 */
@Repository
public class ArticleCategoryDaoImpl implements ArticleCategoryDao {


    @Autowired
    public HibernateSessionFactory hibernateSessionFactory;


    @Override
    @Transactional
    public ArticleCategory getCategoryById(int categoryId) {

        ArticleCategory category = null;
        Session session = hibernateSessionFactory.getSession();
        category = (ArticleCategory) session.get(ArticleCategory.class, categoryId);

        return category;
    }


    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<ArticleCategory> getAllCategories() {

        return (List<ArticleCategory>) hibernateSessionFactory
                    .getSession()
                    .createCriteria(ArticleCategory.class, "articleCategory")
                    .setProjection(Projections.projectionList()
                                    .add(Projections.property("articleCategory.articleCategoryId"), "articleCategoryId")
                                    .add(Projections.property("articleCategory.parentCategoryId"), "parentCategoryId")
                                    .add(Projections.property("articleCategory.title"), "title")
                                    .add(Projections.property("articleCategory.description"), "description")
                    ).setResultTransformer(Transformers.aliasToBean(ArticleCategory.class))
                    .list();
    }


    /**
     ********************************************** Admin Panel ******************************************************
     */

    @Override
    @Transactional
    public ArticleCategory createArticleCategory(ArticleCategory articleCategory) {

        hibernateSessionFactory
                .getSession()
                .save(articleCategory);

        return articleCategory;
    }

    @Override
    @Transactional
    public ArticleCategory updateArticleCategory(ArticleCategory articleCategory) {

        hibernateSessionFactory
                .getSession()
                .saveOrUpdate(articleCategory);

        return articleCategory;
    }

    @Override
    @Transactional
    public void removeArticleCategory(ArticleCategory articleCategory) {

        hibernateSessionFactory
                .getSession()
                .delete(articleCategory);
    }
}
