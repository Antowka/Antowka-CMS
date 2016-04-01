package ru.antowka.service;

import ru.antowka.entity.TicketCategory;

import java.util.List;

/**
 * Interface for ticketCategoryService
 */
public interface TicketCategoryService {

    /**
     * Method response TicketCategory by Id
     *
     * @param categoryId
     *
     * @return
     */
    TicketCategory getCategoryById(int categoryId);

    /**
     * Method response list TicketCategories
     *
     * @return
     */
    List<TicketCategory> getAllCategories();
}
