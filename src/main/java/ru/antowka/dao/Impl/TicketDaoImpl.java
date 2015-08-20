package ru.antowka.dao.Impl;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.*;

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
    public List<Ticket> getAllTickets(int limit, int offset, Order order) {

        Session session = hibernateSessionFactory.getSession();
        return (List<Ticket>)session.createCriteria(Ticket.class, "ticket")
                .setProjection(Projections.projectionList()
                        .add(Projections.property("ticket.ticketId"),"ticketId")
                        .add(Projections.property("ticket.title"), "title")
                ).setResultTransformer(Transformers.aliasToBean(Ticket.class))
                .createAlias("ticket.status", "status")
                .add(Restrictions.eq("status.publicStatus", true))
                .addOrder(order)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @Override
    @Transactional
    public Ticket findTicketById(int ticketId) {

        //todo - added categories collection to property
        Ticket ticket = null;
        Session session = hibernateSessionFactory.getSession();
        ticket = (Ticket)session.createCriteria(Ticket.class, "ticket")
                                .createAlias("ticket.status", "status")
                                .add(Restrictions.eq("ticket.ticketId", ticketId))
                                .add(Restrictions.eq("status.publicStatus", true))
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
        return (int)session.save(ticket);
    }
}
