package ru.antowka.dao;

import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
public interface TicketDao {

    List<Ticket> getAllTickets(int limit, String order, String orderField);
    Ticket findTicketCategoryById(int ticketId);
    List<Ticket> findTicketsByUserOwner(User user);
}
