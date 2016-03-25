package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.TicketStatus;
import ru.antowka.service.TicketService;
import ru.antowka.service.impl.TicketServiceImpl;

import java.util.List;

/**
 * Created by Anton Nik on 02.09.15.
 */

@RestController
@RequestMapping("panel/tickets")
public class TicketsAdminController {

    @Autowired
    private TicketService ticketService;

    /**
     * Response all tickets
     *
     * link:
     *
     * @return
     */
    @RequestMapping(value="get-tickets", method=RequestMethod.GET)
    public @ResponseBody List<Ticket>getAllTickets(org.springframework.web.context.request.WebRequest request){

        return ticketService.getAllTicketsAdmin(Integer.parseInt(request.getParameter("limit")),
                    Integer.parseInt(request.getParameter("offset")),
                    request.getParameter("order"),
                    request.getParameter("orderField"));
    }

    /**
     * Get ticket by ID
     *
     * link: http://localhost:8080/panel/tickets/ticket/55
     *
     * @param ticket
     * @return
     */
    @RequestMapping(value="ticket/{ticketId}", method=RequestMethod.GET)
    public @ResponseBody Ticket getTicket(@ModelAttribute Ticket ticket){
        return ticketService.getTicketByIdAdmin(ticket);
    }

    /**
     * Remove ticket by id
     *
     * link: http://localhost:8080/panel/tickets/remove/5
     *
     * @param ticketId
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="remove/{ticketId}", method=RequestMethod.GET)
    public @ResponseBody MessageResponse removeTicket(@PathVariable("ticketId") int ticketId) {
        return ticketService.removeTicket(ticketId);
    }

    /**
     * Public ticket by id
     *
     * link: http://localhost:8080/panel/tickets/public/5
     *
     * @param ticket
     * @return
     */
    @RequestMapping(value="public/{ticketId}", method= RequestMethod.GET)
    public @ResponseBody Ticket publicTicket(@ModelAttribute Ticket ticket) {

        ticket = ticketService.getTicketByIdAdmin(ticket);

        //TODO - fix problem with lazy load
        ticket.getStatus().setTicketsStatusId(2);

        return ticketService.updateTicketAdmin(ticket);
    }
}
