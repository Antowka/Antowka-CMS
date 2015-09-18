package ru.antowka.dao;

import ru.antowka.entity.TicketStatus;

/**
 * Created by anton on 09.08.15.
 */
public interface TicketStatusDao {

    TicketStatus getStatusById(int statusId);
    int createTicketStatus(TicketStatus ticketStatus);
    void removeTicketStatus(TicketStatus ticketStatus);
}
