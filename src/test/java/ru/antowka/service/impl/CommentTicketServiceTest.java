package ru.antowka.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.dao.CommentDao;
import ru.antowka.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for check CommentTicketService
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentTicketServiceTest {

    @InjectMocks
    CommentTicketService commentTicketService;

    @Mock
    CommentDao commentDao;

    List<Comment> commentsList = new ArrayList<>();

    @Before
    public void setUp() {

        for(int i=1; i <= 5; i++){

            Comment comment = new Comment();
            comment.setCommentId(i);
            comment.setTitle("Test title #" + i);
            commentsList.add(comment);
        }
    }

    @Test
    public void testGetCommentsByEntityId() throws Exception {

        Mockito.when(commentDao.getCommentsByTicketId(1)).thenReturn(commentsList);

        List<Comment> resultComments = commentTicketService.getCommentsByEntityId(1);

        Assert.assertTrue(!resultComments.isEmpty());
    }
}