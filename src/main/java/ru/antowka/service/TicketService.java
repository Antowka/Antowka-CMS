package ru.antowka.service;

import org.springframework.stereotype.Service;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Interface for TicketService
 */
public interface TicketService {

    /**
     * Method response all tickets by pagination (offset, limit, etc)
     *
     * @param limit
     * @param offset
     * @param order
     * @param orderField
     * @return
     */
    List<Ticket> getAllTickets(int limit, int offset, String order, String orderField);

    /**
     * Mthod response Ticket by TicketId
     *
     * @param ticket
     * @return
     */
    Ticket getTicketById(Ticket ticket);

    /**
     * Method response tickets by user-owner
     *
     * @param user
     * @return
     */
    List<Ticket> getTicketsByUser(User user);

    /**
     * Method create new ticket
     *
     * @param ticket
     * @return
     */
    MessageResponse createTicket(Ticket ticket);

    /**
     * Method for remove ticket by ticketId
     *
     * @param ticketId
     * @return
     */
    MessageResponse removeTicket(int ticketId);

    /**
     * Method responseAllTicket with all statuses
     *
     * FOR ONLY ADMINS
     *
     * @param limit
     * @param offset
     * @param order
     * @param orderField
     * @return
     */
    List<Ticket> getAllTicketsAdmin(int limit, int offset, String order, String orderField);

    /**
     * Update status ticket
     *
     * FOR ONLY ADMINS
     *
     * @param ticket
     * @return
     */
    Ticket updateTicketAdmin(Ticket ticket);

    /**
     * Method response any ticket by ticketId
     *
     * FOR ONLY ADMINS
     *
     * @param ticket
     * @return
     */
    Ticket getTicketByIdAdmin(Ticket ticket);


    /**
     * Method count published tickets in system
     *
     * @return
     */
    Long countPublicTickets();
}
