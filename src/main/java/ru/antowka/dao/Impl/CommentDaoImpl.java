package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.CommentDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Comment;
import ru.antowka.entity.Ticket;

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

        Integer commentId = (Integer)session.save(comment);

        comment = (Comment)session.createCriteria(Comment.class, "comment")
                                .add(Restrictions.eq("comment.commentId", commentId))
                                .setProjection(Projections.projectionList()
                                                .add(Projections.property("comment.commentId"), "commentId")
                                                .add(Projections.property("comment.title"), "title")
                                                .add(Projections.property("comment.description"), "description")
                                )
                                .setResultTransformer(Transformers.aliasToBean(Comment.class))
                                .uniqueResult();

        return comment;
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment) {

        Session session = hibernateSessionFactory.getSession();
        session.update(comment);

        return (Comment)session.get(Comment.class, comment.getCommentId());
    }

    @Override
    @Transactional
    public Comment removeComment(int commentId) {

        Session session = hibernateSessionFactory.getSession();

        Comment comment = getCommentById(commentId);
        session.delete(comment);
        comment.setIsDeleted(true);

        return comment;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Comment> getCommentsByTicketId(int ticketId) {

        Session session = hibernateSessionFactory.getSession();

        return (List<Comment>)session.createSQLQuery("SELECT cmts.*, t_cmt.ticket_id FROM comments cmts " +
                "INNER JOIN tickets_comment t_cmt ON cmts.comment_id = t_cmt.comment_id " +
                "WHERE t_cmt.ticket_id="+ticketId)
                .addEntity(Comment.class)
                .list();
    }

    @Override
    @Transactional
    public Comment getCommentById(int commentId) {

        Session session = hibernateSessionFactory.getSession();
        return (Comment)session.get(Comment.class, commentId);
    }
}
