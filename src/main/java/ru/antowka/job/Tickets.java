package ru.antowka.job;

import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.service.TicketService;

/**
 * Created by Anton Nik on 06.09.15.
 */
public class Tickets {

    @Autowired
    private TicketService ticketService;

    public void updateCounterInSettings(){
        //System.out.println("TEST CRON IN SPRING");
    }
}
