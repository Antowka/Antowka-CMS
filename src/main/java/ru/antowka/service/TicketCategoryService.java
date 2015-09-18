package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.TicketCategoryDao;
import ru.antowka.entity.TicketCategory;

import java.util.List;

/**
 * Created by Anton Nik on 06.08.15.
 */
@Service
public class TicketCategoryService {

    @Autowired
    private TicketCategoryDao ticketCategoryDao;


    public TicketCategory getCategoryById(int categoryId){
        return ticketCategoryDao.findTicketCategoryById(categoryId);
    }

    public List<TicketCategory> getAllCategories(){
        return ticketCategoryDao.findAllTicketCategories();
    }
}
