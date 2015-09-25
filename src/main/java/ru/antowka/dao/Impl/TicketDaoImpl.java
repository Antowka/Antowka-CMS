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
                                                    .createAlias("ticket.status", "status")
                                                    .createAlias("ticket.region", "region")
                                                    .setProjection(Projections.projectionList()
                                                                    .add(Projections.property("ticket.ticketId"), "ticketId")
                                                                    .add(Projections.property("ticket.title"), "title")
                                                                    .add(Projections.property("region"), "region")
                                                    ).setResultTransformer(Transformers.aliasToBean(Ticket.class))
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
                                                                        .setMaxResults(1)
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
                                        .createAlias("ticket.region", "region")
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
                                                        .add(Projections.property("region"), "region")
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

        List<Comment> comments = (List<Comment>)session.createSQLQuery("SELECT cmts.*, t_cmt.ticket_id FROM comments cmts " +
                                                                       "INNER JOIN tickets_comment t_cmt ON cmts.comment_id = t_cmt.comment_id " +
                                                                       "WHERE t_cmt.ticket_id="+ticketId)
                                                                        .addEntity(Comment.class)
                                                                        .list();

        ticket.setCategories(new HashSet<TicketCategory>(ticketCategories));
        ticket.setAttachments(new HashSet<Attachment>(ticketAttachments));
        ticket.setComments(new HashSet<Comment>(comments));

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




    /**
     ********************************************** Admin Panel ******************************************************
     */

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public Ticket removeTicket(int ticketId){

        Session session = hibernateSessionFactory.getSession();
        Ticket ticket = (Ticket)session.get(Ticket.class, ticketId);

        //get attachments for ticket
        List<Attachment> ticketAttachments = (List<Attachment>)session.createCriteria(Attachment.class, "att")
                .createAlias("att.tickets", "tickets")
                .add(Restrictions.eq("tickets.ticketId", ticketId))
                .list();


        session.delete(ticket);
        ticket.setIsDeleted(true);
        ticket.setAttachments(new HashSet<Attachment>(ticketAttachments));

        return ticket;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Ticket> getAllTicketsAdmin(int limit, int offset, Order order) {

        Session session = hibernateSessionFactory.getSession();

        return  (List<Ticket>)session.createCriteria(Ticket.class, "ticket")
                                                    .addOrder(order)
                                                    .setFirstResult(offset)
                                                    .setMaxResults(limit)
                                                    .list();
    }

    @Override
    @Transactional
    public void updateTicketAdmin(Ticket ticket){

        Session session = hibernateSessionFactory.getSession();
        session.update(ticket);
    }

    @Override
    @Transactional
    public Ticket findTicketByIdAdmin(int ticketId){

        Session session = hibernateSessionFactory.getSession();
        return (Ticket)session.get(Ticket.class, ticketId);
    }

    @Override
    @Transactional
    public Long countPublicTickets(){

        Session session = hibernateSessionFactory.getSession();

        return (Long) session.createCriteria(Ticket.class, "ticket")
                                .createAlias("ticket.status", "status")
                                .add(Restrictions.eq("status.publicStatus", true))
                                .setProjection(Projections.rowCount())
                                .uniqueResult();

    }
}
