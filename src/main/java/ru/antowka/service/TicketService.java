package ru.antowka.service;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.MessageResponse;
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

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private MessageResponse messageResponse;

    public List<Ticket> getAllTickets(int limit, int offset, String order, String orderField){

        Order orderObj = null;

        switch (order){
            case "asc":

                orderObj = Order.asc(orderField);

                break;

            case "desc":

                orderObj = Order.desc(orderField);

                break;

            default:

                orderObj = Order.desc(orderField);

                break;
        }

        return ticketDao.getAllTickets(limit, offset, orderObj);
    }

    public Ticket getTicketById(int ticketId){
        return  ticketDao.findTicketById(ticketId);
    }

    public List<Ticket> getTicketsByUser(User user){
        return ticketDao.findTicketsByUserOwner(user);
    }

    public MessageResponse createTicket(Ticket ticket){

        Integer ticketId = ticketDao.createTicket(ticket);

        if(!ticketId.equals(0)){

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful");
            messageResponse.setMessage("You ticket added to system with #"+ticketId);
        } else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Ticket has not been saved");
            messageResponse.setMessage("You ticket added to system with #"+ticketId);
        }

        return messageResponse;
    }

    public MessageResponse removeTicket(int ticketId){

        Ticket ticket = ticketDao.removeTicket(ticketId);

        if(ticket.isDeleted()){

            //remove attachments
            ticket.getAttachments().stream().forEach(attachment -> {
                attachmentService.removeAttachment(attachment.getAttachmentId());
            });

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful");
            messageResponse.setMessage("Your ticket #" + ticket.getTicketId() + " removed from system");
        } else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Ticket has not been removed");
            messageResponse.setMessage("Your ticket #" + ticketId + " removed to system");
        }

        return messageResponse;
    }
}
