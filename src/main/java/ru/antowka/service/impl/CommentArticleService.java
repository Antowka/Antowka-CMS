package ru.antowka.service.impl;

import ru.antowka.entity.Comment;
import ru.antowka.service.CommentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for working with CommentArticle
 */
public class CommentArticleService extends CommentService {

    @Override
    public List<Comment> getCommentsByEntityId(int ticketId) {
        return new ArrayList<Comment>();
    }
}
