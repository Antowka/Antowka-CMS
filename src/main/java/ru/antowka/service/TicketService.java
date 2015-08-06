package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;


    public Ticket getTicketById(int ticketId){
        return ticketDao.findTicketCategoryById(ticketId);
    }

    public List<Ticket> getTicketsByUser(User user){
        return ticketDao.findTicketsByUserOwner(user);
    }
}
