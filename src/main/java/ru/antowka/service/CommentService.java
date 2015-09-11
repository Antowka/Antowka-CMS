package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.antowka.dao.CommentDao;
import ru.antowka.entity.Comment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;

/**
 * Created by Anton Nik on 09.09.15.
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public Comment createComment(Comment comment){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(user);

        return commentDao.createComment(comment);
    }

    public MessageResponse removeComment(int commentId){

        commentDao.removeComment(commentId);

        return null;
    }

    public Comment updateComment(Comment comment){
        return commentDao.updateComment(comment);
    }
}
