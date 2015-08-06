package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.CategoryArticleDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Article;
import ru.antowka.entity.CategoryArticle;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
@Repository
public class CategoryArticleDaoImpl implements CategoryArticleDao {


    @Autowired
    public HibernateSessionFactory hibernateSessionFactory;


    @Override
    @Transactional
    public CategoryArticle findCategoryById(int categoryId) {

        CategoryArticle category = null;
        Session session = hibernateSessionFactory.getSession();
        category = (CategoryArticle) session.get(CategoryArticle.class, categoryId);

        return category;
    }


    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public Set<CategoryArticle> findAllCategories() {

        Set<CategoryArticle> categories = null;
        Session session = hibernateSessionFactory.getSession();
        categories = (Set<CategoryArticle>) session.createCriteria(CategoryArticle.class).list();

        return categories;
    }
}
