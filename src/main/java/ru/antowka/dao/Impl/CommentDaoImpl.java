package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.CommentDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Comment;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton Nik on 09.09.15.
 */
@Repository
public class CommentDaoImpl implements CommentDao{

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public Comment createComment(Comment comment) {

        Session session = hibernateSessionFactory.getSession();

        return null;
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment) {

        Session session = hibernateSessionFactory.getSession();

        return null;
    }

    @Override
    @Transactional
    public Comment removeComment(int commentId) {

        Session session = hibernateSessionFactory.getSession();

        return null;
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByTicketId(int ticketId) {

        Session session = hibernateSessionFactory.getSession();

        return null;
    }

    @Override
    @Transactional
    public Comment getCommentById(int commentId) {

        Session session = hibernateSessionFactory.getSession();

        return null;
    }
}
