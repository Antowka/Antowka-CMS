package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;
import ru.antowka.service.TicketCategoryService;
import ru.antowka.service.TicketService;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@RestController
@RequestMapping("tickets")
public class TicketsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketCategoryService ticketCategoryService;

    /**
     * Response tickets by a few params
     *
     * Link: http://localhost:8080/tickets/get-tickets/?limit=2&orderField=creationDate&order=desc
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "get-tickets", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getTicketsAll(org.springframework.web.context.request.WebRequest request){

        List<Ticket> tickets = null;

        tickets = ticketService.getAllTickets(Integer.parseInt(request.getParameter("limit")),
                                                               request.getParameter("order"),
                                                               request.getParameter("orderField"));

        return tickets;
    }

    /**
     * Response ticket by ID over JSON-response
     *
     * link: http://localhost:8080/tickets/get-ticket/?ticketId=1
     *
     * @param ticket
     * @return
     */
    @RequestMapping(value = "get-ticket", method = RequestMethod.GET)
    public @ResponseBody Ticket getTicket(@ModelAttribute Ticket ticket){
        ticket = ticketService.getTicketById(ticket.getTicketId());
        return ticket;
    }

    /**
     * Response tickets list by User over JSON-response
     *
     * link: http://localhost:8080/tickets/get-tickets-by-user/?userId=1
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "get-tickets-by-user", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getTicketsByUser(@ModelAttribute User user){

        List<Ticket> tickets = null;
        tickets = ticketService.getTicketsByUser(user);

        return tickets;
    }


    @RequestMapping(value = "create-ticket", method = RequestMethod.POST)
    public @ResponseBody Ticket createTicket(@RequestBody Ticket ticket){

        String test ="d";

        return ticket;
    }

}
