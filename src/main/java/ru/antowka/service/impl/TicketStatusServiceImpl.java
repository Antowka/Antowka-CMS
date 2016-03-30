package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketStatusDao;
import ru.antowka.entity.TicketStatus;
import ru.antowka.service.TicketStatusService;

/**
 * Created by anton on 30.03.16.
 */
@Service
public class TicketStatusServiceImpl implements TicketStatusService {

    @Autowired
    TicketStatusDao ticketStatusDao;

    @Override
    public TicketStatus getTicketStatus(int ticketStatusId) {
        return ticketStatusDao.getStatusById(ticketStatusId);
    }
}
