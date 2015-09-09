package ru.antowka.dao;

import ru.antowka.entity.Comment;

import java.util.List;

/**
 * Created by Anton Nik on 09.09.15.
 */
public interface CommentDao {

    Comment createComment(Comment comment);
    Comment updateComment(Comment comment);
    Comment removeComment(int commentId);
    List<Comment> getCommentsByTicketId(int ticketId);
    Comment getCommentById(int commentId);
}
