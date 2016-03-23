package ru.antowka.service.impl;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.antowka.dao.TicketDao;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Ticket;
import ru.antowka.entity.User;
import ru.antowka.service.EmailSender;
import ru.antowka.service.MessageResponseService;
import ru.antowka.utils.UtilsHibernate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test check TicketService
 */
@RunWith(PowerMockRunner.class)
public class TicketServiceImplTest {

    @InjectMocks
    TicketServiceImpl ticketService;

    @Mock
    private TicketDao ticketDao;

    @Mock
    private EmailSender emailSender;

    List<Ticket> ticketList = new ArrayList<>();

    @Before
    public void setUp() {

        Ticket ticket = null;

        MessageResponseServiceImpl messageResponseService = new MessageResponseServiceImpl();
        messageResponseService.setMessageResponse(new MessageResponse());

        ticketService.setMessageResponseService(messageResponseService);

        for(int i=1; i <= 5; i++){

            ticket = new Ticket();
            ticket.setTicketId(i);
            ticket.setTitle("Check long TITLE Check long TITLE Check long TITLE Check long TITLE Check long TITLE Ticket#" + i);
            ticket.setIsDeleted(false);
            ticket.setDescription("Ticket description #" + i);
            ticketList.add(ticket);
        }
    }

    @Test
    @PrepareForTest({UtilsHibernate.class})
    public void testGetAllTickets() throws Exception {

        PowerMockito.mockStatic(UtilsHibernate.class);

        Mockito.when(ticketDao.getAllTickets(10, 0, UtilsHibernate.getOrderByString("ticketId", "ASC")))
               .thenReturn(ticketList);

        List<Ticket> results = ticketService.getAllTickets(10, 0, "ticketId", "ASC");

        assertTrue(results.get(0).getTitle().length() < 50);
    }

    @Test
    public void testGetTicketById() throws Exception {

        Ticket ticket = new Ticket();
        ticket.setTicketId(1);
        Mockito.when(ticketDao.findTicketById(ticket.getTicketId())).thenReturn(ticket);
        ticket = ticketService.getTicketById(ticket);

        assertEquals(ticket.getTicketId(), 1);
    }

    @Test
    public void testGetTicketsByUser() throws Exception {

        User user = new User();
        Mockito.when(ticketDao.findTicketsByUserOwner(user)).thenReturn(ticketList);
        List<Ticket> result = ticketService.getTicketsByUser(user);

        assertEquals(result, ticketList);
    }

    @Test
    public void testCreateTicket() throws Exception {

        Ticket ticket = new Ticket();
        ticket.setTicketId(1);
        Mockito.when(ticketDao.createTicket(ticket)).thenReturn(ticket);
        Mockito.doCallRealMethod().when(emailSender).newTicketCreated(ticket.getEmail());

        MessageResponse messageResponse = ticketService.createTicket(ticket);
        assertTrue(messageResponse.getCode() == 1);
    }

    @Test
    public void testRemoveTicket() throws Exception {

    }

    @Test
    public void testGetAllTicketsAdmin() throws Exception {

    }

    @Test
    public void testUpdateStatusOnTicketAdmin() throws Exception {

    }

    @Test
    public void testGetTicketByIdAdmin() throws Exception {

    }

    @Test
    public void testCountPublicTickets() throws Exception {

    }
}