package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketDao;
import ru.antowka.dao.TicketStatusDao;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.TicketStatus;
import ru.antowka.entity.User;
import ru.antowka.service.EmailSender;
import ru.antowka.service.MessageResponseService;
import ru.antowka.service.TicketService;
import ru.antowka.service.impl.AttachmentServiceImpl;
import ru.antowka.utils.UtilsHibernate;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private AttachmentServiceImpl attachmentServiceImpl;

    @Autowired
    private TicketStatusDao ticketStatusDao;

    @Autowired
    private MessageResponseService messageResponseService;

    @Autowired
    EmailSender emailSender;

    public List<Ticket> getAllTickets(int limit, int offset, String order, String orderField){

        List<Ticket> tickets = ticketDao.getAllTickets(
                limit,
                offset,
                UtilsHibernate.getOrderByString(order, orderField)
        );

        //cut long title
        //todo - set 45 to properties
        tickets.stream().forEach(ticket->{
            if(ticket.getTitle().length() > 45) {
                ticket.setTitle(trimString(ticket.getTitle(), 45, false));
            }
        });

        return tickets;
    }

    public Ticket getTicketById(Ticket ticket){
        return  ticketDao.findTicketById(ticket.getTicketId());
    }

    public List<Ticket> getTicketsByUser(User user){
        return ticketDao.findTicketsByUserOwner(user);
    }

    public MessageResponse createTicket(Ticket ticket){

        ticket = ticketDao.createTicket(ticket);

        //send email about create ticket
        if(ticket.getTicketId() != 0) {
            emailSender.newTicketCreated(ticket.getEmail());
        }

        return messageResponseService.getResponseForCreateEntity(
                ticket.getTicketId() != 0,
                ticket.getEntityName(),
                ticket.getTicketId()
        );
    }

    public MessageResponse removeTicket(int ticketId){

        Ticket ticket = ticketDao.removeTicket(ticketId);

        //remove attachments
        if(ticket.isDeleted()){

            ticket.getAttachments().stream().forEach(attachment -> {
                attachmentServiceImpl.removeAttachment(attachment.getAttachmentId());
            });
        }

        return messageResponseService.getResponseForRemoveEntity(
                    ticket.isDeleted(),
                    ticket.getEntityName(),
                    ticket.getTicketId()
        );
    }

    /**
     ************************************************ Admin Panel *****************************************************
     */
    public List<Ticket> getAllTicketsAdmin(int limit, int offset, String order, String orderField){


        return ticketDao.getAllTicketsAdmin(limit, offset, UtilsHibernate.getOrderByString(order, orderField));
    }

    public MessageResponse updateStatusOnTicketAdmin(int ticketId, int statusId){

        Ticket ticket = ticketDao.findTicketByIdAdmin(ticketId);
        TicketStatus status = ticketStatusDao.getStatusById(statusId);
        ticket.setStatus(status);
        ticketDao.updateTicketAdmin(ticket);

        return messageResponseService.getResponseForRemoveEntity(
                ticket.getTicketId() == ticketId,
                ticket.getEntityName(),
                ticket.getTicketId()
        );
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


    /**
     * *************************** Getters and Setters **********************************
     */

    public MessageResponseService getMessageResponseService() {
        return messageResponseService;
    }

    public void setMessageResponseService(MessageResponseService messageResponseService) {
        this.messageResponseService = messageResponseService;
    }
}
