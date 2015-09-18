package ru.antowka.dao.Impl;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.TicketStatusDao;
import ru.antowka.entity.TicketStatus;

import javax.transaction.Transactional;

/**
 * Created by Anton Nikanorov on 09.08.15.
 */
@Repository
public class TicketStatusDaoImpl implements TicketStatusDao {

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public TicketStatus getStatusById(int statusId) {

        TicketStatus ticketStatus = null;
        Session session = hibernateSessionFactory.getSession();
        ticketStatus = (TicketStatus)session.get(TicketStatus.class, statusId);

        return ticketStatus;
    }

    @Override
    @Transactional
    public int createTicketStatus(TicketStatus ticketStatus) {

        Session session = hibernateSessionFactory.getSession();
        return (int)session.save(ticketStatus);
    }

    @Override
    @Transactional
    public void removeTicketStatus(TicketStatus ticketStatus) {

        Session session = hibernateSessionFactory.getSession();
        session.delete(ticketStatus);
    }
}
