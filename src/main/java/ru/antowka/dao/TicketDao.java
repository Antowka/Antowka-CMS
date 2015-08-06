package ru.antowka.dao;

import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
public interface TicketDao {

    public Ticket findTicketCategoryById(int ticketId);
    public List<Ticket> findTicketsByUserOwner(User user);
}
