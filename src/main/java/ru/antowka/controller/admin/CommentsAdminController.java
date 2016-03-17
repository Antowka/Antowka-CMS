package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.Comment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.service.CommentService;

import java.util.List;

/**
 * Created by Anton Nik on 09.09.15.
 */
@RestController
@RequestMapping("panel/comments")
public class CommentsAdminController {

    @Autowired
    private CommentService commentService;

    /**
     * Create comment for Ticket
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public @ResponseBody Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

    /**
     * Remove comment by commentID
     *
     * Link: http://localhost:8080/panel/comments/remove/5
     *
     * @param commentId
     * @return
     */
    @RequestMapping(value="remove/{commentId}", method=RequestMethod.GET)
    public @ResponseBody
    MessageResponse removeTicket(@PathVariable("commentId") int commentId){
        return commentService.removeComment(commentId);
    }

    /**
     * Get comments list by TicketID
     *
     * Link: http://localhost:8080/panel/comments/getByTask/5
     *
     * @param ticketId
     * @return
     */
    @RequestMapping(value="getByTask/{ticketId}", method=RequestMethod.GET)
    public @ResponseBody
    List<Comment> getCommentsByTicketsById(@PathVariable("ticketId") int ticketId){
        return commentService.getCommentsByEntityId(ticketId);
    }
}
