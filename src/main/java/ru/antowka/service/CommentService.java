package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.antowka.dao.CommentDao;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.Comment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Interface for work with Comments
 */
public abstract class CommentService {

    @Autowired
    protected CommentDao commentDao;

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected MessageResponse messageResponse;

    public Comment createComment(Comment comment){

        org.springframework.security.core.userdetails.User userDetail =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
        User user = userDao.findByUserName(userDetail.getUsername());
        comment.setUser(user);

        return commentDao.createComment(comment);
    }

    public MessageResponse removeComment(int commentId){

        Comment comment = commentDao.removeComment(commentId);

        if(comment.isDeleted()){

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful");
            messageResponse.setMessage("Your ticket #" + comment.getCommentId() + " updated in system");
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Comment has not been updated");
            messageResponse.setMessage("Your comment #" + comment.getCommentId() + " removed from system");
        }

        return messageResponse;
    }

    public Comment updateComment(Comment comment){
        return commentDao.updateComment(comment);
    }

    public abstract List<Comment> getCommentsByEntityId(int ticketId);
}
