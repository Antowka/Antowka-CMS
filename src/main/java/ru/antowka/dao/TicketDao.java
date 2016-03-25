package ru.antowka.dao;

import org.hibernate.criterion.Order;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
public interface TicketDao {

    List<Ticket> getAllTickets(int limit, int offset, Order order);
    Ticket findTicketById(int ticketId);
    List<Ticket> findTicketsByUserOwner(User user);
    Ticket createTicket(Ticket ticket);
    Ticket removeTicket(int ticketId);
    List<Ticket> getAllTicketsAdmin(int limit, int offset, Order order);

    Ticket updateTicketAdmin(Ticket ticket);
    Ticket findTicketByIdAdmin(int ticketId);
    Long countPublicTickets();
}
