package ru.antowka.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.dao.TicketStatusDao;
import ru.antowka.entity.TicketStatus;

import static org.junit.Assert.*;

/**
 * Test check TicketStatusService
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketStatusServiceImplTest {

    @InjectMocks
    TicketStatusServiceImpl ticketStatusService;

    @Mock
    TicketStatusDao ticketStatusDao;

    @Test
    public void getTicketStatus() throws Exception {

        TicketStatus ticketStatus = new TicketStatus();
        ticketStatus.setTicketsStatusId(1);
        ticketStatus.setStatus("PUBLIC");

        Mockito.when(ticketStatusDao.getStatusById(1)).thenReturn(ticketStatus);

        ticketStatusService.getTicketStatus(1);

        assertEquals(ticketStatus.getStatus(), "PUBLIC");
    }
}