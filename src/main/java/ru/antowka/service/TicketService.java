package ru.antowka.service;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.common.EmailSender;
import ru.antowka.dao.TicketDao;
import ru.antowka.dao.TicketStatusDao;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.TicketStatus;
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
    private TicketStatusDao ticketStatusDao;

    @Autowired
    private MessageResponse messageResponse;

    @Autowired
    EmailSender emailSender;

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

        List<Ticket> tickets = ticketDao.getAllTickets(limit, offset, orderObj);

        //cut long title
        //todo - set 45 to properties
        tickets.stream().forEach(ticket->{
            if(ticket.getTitle().length() > 45) {
                ticket.setTitle(trimString(ticket.getTitle(), 45, false));
            }
        });

        return tickets;
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

        emailSender.newTicketCreated(ticket.getEmail());

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

    /**
     ************************************************ Admin Panel *****************************************************
     */
    public List<Ticket> getAllTicketsAdmin(int limit, int offset, String order, String orderField){

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

        return ticketDao.getAllTicketsAdmin(limit, offset, orderObj);
    }

    public MessageResponse updateStatusOnTicketAdmin(int ticketId, int statusId){

        Ticket ticket = ticketDao.findTicketByIdAdmin(ticketId);
        TicketStatus status = ticketStatusDao.getStatusById(statusId);
        ticket.setStatus(status);
        ticketDao.updateTicketAdmin(ticket);



        if(ticket.getTicketId() == ticketId) {

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful");
            messageResponse.setMessage("Your ticket #" + ticket.getTicketId() + " updated in system");
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Ticket has not been updated");
            messageResponse.setMessage("Your ticket #" + ticketId + " has not updated in system");
        }

        return messageResponse;
    }

    public Ticket getTicketByIdAdmin(int ticketId) {
        return ticketDao.findTicketByIdAdmin(ticketId);
    }

    /**
     * Response count public tickets
     *
     * @return
     */
    public Long countPublicTickets(){
        return ticketDao.countPublicTickets();
    }


    /**
     * *************************** PRIVATE METHODS **************************************
     */
    //todo - replace to static utils
    private String trimString(String string, int length, boolean soft) {

        if(string == null || string.trim().isEmpty()){
            return string;
        }

        StringBuffer sb = new StringBuffer(string);
        int actualLength = length - 3;
        if(sb.length() > actualLength){
            if(!soft) {
                return sb.insert(actualLength, "...").substring(0, actualLength + 3);
            } else {
                int endIndex = sb.indexOf(" ",actualLength);
                return sb.insert(endIndex,"...").substring(0, endIndex+3);
            }
        }
        return string;
    }
}
