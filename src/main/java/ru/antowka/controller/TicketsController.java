package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;
import ru.antowka.service.TicketCategoryService;
import ru.antowka.service.TicketService;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Controller
public class TicketsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketCategoryService ticketCategoryService;

    @RequestMapping(value = "/tickets/get-tickets", method = RequestMethod.GET)
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
    @RequestMapping(value = "/tickets/get-ticket", method = RequestMethod.GET)
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
    @RequestMapping(value = "/tickets/get-tickets-by-user", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getTicketsByUser(@ModelAttribute User user){

        List<Ticket> tickets = null;
        tickets = ticketService.getTicketsByUser(user);

        return tickets;
    }

}
