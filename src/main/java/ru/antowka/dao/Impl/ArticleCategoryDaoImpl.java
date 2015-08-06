package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.ArticleCategory;

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
    public ArticleCategory findCategoryById(int categoryId) {

        ArticleCategory category = null;
        Session session = hibernateSessionFactory.getSession();
        category = (ArticleCategory) session.get(ArticleCategory.class, categoryId);

        return category;
    }


    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<ArticleCategory> findAllCategories() {

        List<ArticleCategory> categories = null;
        Session session = hibernateSessionFactory.getSession();
        categories = (List<ArticleCategory>) session.createCriteria(ArticleCategory.class).list();

        return categories;
    }
}
