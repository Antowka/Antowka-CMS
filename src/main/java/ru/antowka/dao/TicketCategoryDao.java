package ru.antowka.dao;

import ru.antowka.entity.Ticket;
import ru.antowka.entity.TicketCategory;

import java.util.List;


/**
 * Created by anton on 06.08.15.
 */
public interface TicketCategoryDao {

    TicketCategory findTicketCategoryById(int ticketCategoryId);
    List<TicketCategory> findAllTicketCategories();
}
