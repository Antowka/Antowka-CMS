package ru.antowka.service;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    public List<Ticket> getAllTickets(int limit, String order, String orderField){

        Order orderObj = null;

        switch (order){
            case "asc":

                orderObj = Order.asc(orderField);

                break;

            case "desc":

                orderObj = Order.desc(orderField);

                break;

            default:

                orderObj = Order.desc(orderField);

                break;
        }

        return ticketDao.getAllTickets(limit, orderObj);
    }

    public Ticket getTicketById(int ticketId){
        return ticketDao.findTicketCategoryById(ticketId);
    }

    public List<Ticket> getTicketsByUser(User user){
        return ticketDao.findTicketsByUserOwner(user);
    }

    public Ticket createTicket(Ticket ticket){

        Integer ticketId = ticketDao.createTicket(ticket);
        ticket = this.getTicketById(ticketId);
        return ticket;
    }
}
