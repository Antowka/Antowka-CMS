package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.AttachmentDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 16.08.15.
 */
@Repository
public class AttachmentDaoImpl implements AttachmentDao {

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public Attachment findAttachmentById(int attachmentId) {

        Session session = hibernateSessionFactory.getSession();
        return (Attachment)session.get(Attachment.class, attachmentId);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Attachment> getAttachmentByUser(User user) {

        Session session = hibernateSessionFactory.getSession();
        return (List<Attachment>)session.createCriteria(Attachment.class)
                                        .add(Restrictions.eq("userOwnerId", user.getUserId()))
                                        .list();
    }

    @Override
    @Transactional
    public  List<Attachment> createAttachments(List<Attachment> attachments) {

        Session session = hibernateSessionFactory.getSession();

        attachments.stream().forEach(attachment -> {
            attachment.setAttachmentId((int) session.save(attachment));
        });

        return attachments;
    }
}
