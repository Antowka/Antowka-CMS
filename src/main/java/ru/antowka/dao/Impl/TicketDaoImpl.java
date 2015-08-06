package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Repository
public class TicketDaoImpl implements TicketDao{

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public Ticket findTicketCategoryById(int ticketId) {

        Ticket ticket = null;
        Session session = hibernateSessionFactory.getSession();
        ticket = (Ticket)session.get(Ticket.class, ticketId);

        return ticket;
    }


    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Ticket> findTicketsByUserOwner(User user) {

        List<Ticket> articles = null;

        Session session = hibernateSessionFactory.getSession();
        articles = (List<Ticket>)session.createCriteria(Ticket.class)
                .add(Restrictions.eq("userOwnerId", user.getUserId()))
                .list();

        return articles;
    }
}
