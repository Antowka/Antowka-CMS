package ru.antowka.dao;

import org.hibernate.criterion.Order;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
public interface TicketDao {

    List<Ticket> getAllTickets(int limit, Order order);
    Ticket findTicketById(int ticketId);
    List<Ticket> findTicketsByUserOwner(User user);
    int createTicket(Ticket ticket);
}
