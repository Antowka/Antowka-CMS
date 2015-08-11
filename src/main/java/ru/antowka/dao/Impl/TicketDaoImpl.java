package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.TicketStatus;
import ru.antowka.entity.User;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by anton on 06.08.15.
 */
@Repository
public class TicketDaoImpl implements TicketDao{

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;


    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Ticket> getAllTickets(int limit, Order order) {

        List<Ticket> tickets = null;
        Session session = hibernateSessionFactory.getSession();
        tickets = (List<Ticket>)session.createCriteria(Ticket.class, "ticket")
                .createAlias("ticket.status", "status")
                .add(Restrictions.eq("status.publicStatus", true))
                .addOrder(order)
                .setMaxResults(limit)
                .list();

        return tickets;
    }

    @Override
    @Transactional
    public Ticket findTicketById(int ticketId) {

        Ticket ticket = null;
        Session session = hibernateSessionFactory.getSession();
        ticket = (Ticket)session.createCriteria(Ticket.class, "ticket")
                                .createAlias("ticket.status", "status")
                                .add(Restrictions.eq("status.publicStatus", true))
                                .add(Restrictions.eq("ticket.ticketId", ticketId))
                                .uniqueResult();

        return ticket;
    }


    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Ticket> findTicketsByUserOwner(User user) {

        List<Ticket> articles = null;

        Session session = hibernateSessionFactory.getSession();
        articles = (List<Ticket>)session.createCriteria(Ticket.class, "ticket")
                .createAlias("ticket.status", "status")
                .add(Restrictions.eq("status.publicStatus", true))
                .add(Restrictions.eq("ticket.userOwnerId", user.getUserId()))
                .list();

        return articles;
    }

    @Override
    @Transactional
    public int createTicket(Ticket ticket) {
        Session session = hibernateSessionFactory.getSession();
        Integer ticketId = (int)session.save(ticket);
        return ticketId;
    }
}
