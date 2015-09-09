package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.Comment;
import ru.antowka.service.CommentService;

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

}
