package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.MessageResponse;
import ru.antowka.service.TicketService;

/**
 * Created by Anton Nik on 02.09.15.
 */

@RestController
@RequestMapping("panel/tickets")
public class TicketsAdminController {

    @Autowired
    private TicketService ticketService;

    /**
     * Remove ticket by id
     *
     * link: http://localhost:8080/panel/tickets/remove/5
     *
     * @param ticketId
     * @return
     */
    @RequestMapping(value="remove/{ticketId}", method= RequestMethod.GET)
    public @ResponseBody MessageResponse removeTicket(@PathVariable("ticketId") int ticketId) {
        return ticketService.removeTicket(ticketId);
    }
}
