package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.TicketCategoryDao;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.TicketCategory;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Repository
public class TicketCategoryDaoImpl implements TicketCategoryDao {

    @Autowired
    public HibernateSessionFactory hibernateSessionFactory;


    @Override
    @Transactional
    public TicketCategory findTicketCategoryById(int ticketCategoryId) {

        TicketCategory ticketCategory = null;
        Session session = hibernateSessionFactory.getSession();
        ticketCategory = (TicketCategory) session.get(TicketCategory.class, ticketCategoryId);

        return ticketCategory;
    }


    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<TicketCategory> findAllTicketCategories() {

        List<TicketCategory> tickets = null;
        Session session = hibernateSessionFactory.getSession();
        tickets = (List<TicketCategory>) session.createCriteria(TicketCategory.class).list();

        return tickets;
    }
}
