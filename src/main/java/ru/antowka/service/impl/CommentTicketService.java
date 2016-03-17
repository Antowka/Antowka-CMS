package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.antowka.dao.CommentDao;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.Comment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;
import ru.antowka.service.CommentService;

import java.util.List;

/**
 * Service for working with CommentTickets
 */
@Service
public class CommentTicketService extends CommentService{

    @Override
    public List<Comment> getCommentsByEntityId(int ticketId){
        return commentDao.getCommentsByTicketId(ticketId);
    }
}
