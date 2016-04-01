package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketCategoryDao;
import ru.antowka.entity.TicketCategory;
import ru.antowka.service.TicketCategoryService;

import java.util.List;

/**
 * Interface for ticketCategoryService
 */
@Service
public class TicketCategoryServiceImpl implements TicketCategoryService{

    @Autowired
    private TicketCategoryDao ticketCategoryDao;


    public TicketCategory getCategoryById(int categoryId){
        return ticketCategoryDao.findTicketCategoryById(categoryId);
    }

    public List<TicketCategory> getAllCategories(){
        return ticketCategoryDao.findAllTicketCategories();
    }
}
