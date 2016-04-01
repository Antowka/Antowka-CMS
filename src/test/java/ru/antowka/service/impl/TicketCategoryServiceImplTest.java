package ru.antowka.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.dao.TicketCategoryDao;
import ru.antowka.entity.TicketCategory;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test check TicketCategoryService
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketCategoryServiceImplTest {

    @Mock
    private TicketCategoryDao ticketCategoryDao;

    @InjectMocks
    private TicketCategoryServiceImpl ticketCategoryService;

    @Test
    public void getCategoryById() throws Exception {

        TicketCategory ticketCategory = new TicketCategory();
        ticketCategory.setTicketCategoryId(1);
        ticketCategory.setTitle("Demo ticketCategory");

        Mockito.when(ticketCategoryDao.findTicketCategoryById(1)).thenReturn(ticketCategory);

        assertEquals(ticketCategory.getTicketCategoryId(), 1);
    }

    @Test
    public void getAllCategories() throws Exception {

        List<TicketCategory> ticketCategories = new ArrayList<>();

        //create list with ticketCategories
        for(int i = 0; i < 5; i++){
            TicketCategory ticketCategory = new TicketCategory();
            ticketCategory.setTicketCategoryId(i);
            ticketCategory.setTitle("TicketCategory #" + i);
            ticketCategories.add(ticketCategory);
        }

        Mockito.when(ticketCategoryDao.findAllTicketCategories()).thenReturn(ticketCategories);

        ticketCategories = ticketCategoryService.getAllCategories();

        assertEquals(ticketCategories.get(3).getTicketCategoryId(), 3);
    }
}