package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.*;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Anton nik on 06.08.15.
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
        List<Ticket> tickets = (List<Ticket>)session.createCriteria(Ticket.class, "ticket")
                                                    .setProjection(Projections.projectionList()
                                                                    .add(Projections.property("ticket.ticketId"), "ticketId")
                                                                    .add(Projections.property("ticket.title"), "title")
                                                    ).setResultTransformer(Transformers.aliasToBean(Ticket.class))
                                                    .createAlias("ticket.status", "status")
                                                    .add(Restrictions.eq("status.publicStatus", true))
                                                    .addOrder(order)
                                                    .setFirstResult(offset)
                                                    .setMaxResults(limit)
                                                    .list();

        //get attachments for ticket
        tickets.stream().forEach(ticket -> {

            List<Attachment> ticketAttachments = (List<Attachment>)session.createCriteria(Attachment.class, "att")
                                                                        .createAlias("att.tickets", "tickets")
                                                                        .add(Restrictions.eq("tickets.ticketId", ticket.getTicketId()))
                                                                        .add(Restrictions.like("att.mimeType", "image/%"))
                                                                        .list();

            ticket.setAttachments(new HashSet<Attachment>(ticketAttachments));
        });

        return tickets;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public Ticket findTicketById(int ticketId) {

        Session session = hibernateSessionFactory.getSession();
        Ticket ticket = (Ticket)session.createCriteria(Ticket.class, "ticket")
                                        .createAlias("ticket.status", "status")
                                        .add(Restrictions.eq("ticket.ticketId", ticketId))
                                        .add(Restrictions.eq("status.publicStatus", true))
                                        .setProjection(Projections.projectionList()
                                                        .add(Projections.property("ticket.ticketId"), "ticketId")
                                                        .add(Projections.property("ticket.title"), "title")
                                                        .add(Projections.property("ticket.description"), "description")
                                                        .add(Projections.property("ticket.address"), "address")
                                                        .add(Projections.property("ticket.firstName"), "firstName")
                                                        .add(Projections.property("ticket.creationDate"), "creationDate")
                                                        .add(Projections.property("status"), "status")
                                        )
                                        .setResultTransformer(Transformers.aliasToBean(Ticket.class))
                                        .uniqueResult();

        //get categories for tickets
        List<TicketCategory> ticketCategories = (List<TicketCategory>)session.createCriteria(TicketCategory.class, "tc")
                                                                             .createAlias("tc.tickets", "tickets")
                                                                             .add(Restrictions.eq("tickets.ticketId", ticketId))
                                                                             .list();
        //get attachments for ticket
        List<Attachment> ticketAttachments = (List<Attachment>)session.createCriteria(Attachment.class, "att")
                                                                            .createAlias("att.tickets", "tickets")
                                                                            .add(Restrictions.eq("tickets.ticketId", ticketId))
                                                                            .list();

        ticket.setCategories(new HashSet<TicketCategory>(ticketCategories));
        ticket.setAttachments(new HashSet<Attachment>(ticketAttachments));

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

    @Override
    @Transactional
    public Ticket removeTicket(int ticketId){

        Session session = hibernateSessionFactory.getSession();
        Ticket ticket = (Ticket)session.get(Ticket.class, ticketId);
        session.delete(ticket);
        ticket.setIsDeleted(true);

        return ticket;
    }
}
