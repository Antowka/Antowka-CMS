package ru.antowka.service;

import ru.antowka.entity.TicketStatus;

/**
 * Created by anton on 30.03.16.
 */
public interface TicketStatusService {

    /**
     * Response ticketStatus object by id
     *
     * @param ticketStatusId
     * @return
     */
    TicketStatus getTicketStatus(int ticketStatusId);
}
