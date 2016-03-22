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

import static org.junit.Assert.*;

/**
 * Test for check CommentArticleService
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentArticleServiceTest {

    @InjectMocks
    CommentArticleService commentArticleService;

    @Mock
    CommentDao commentDao;

    List<Comment> comments = new ArrayList<>();

    @Before
    public void setUp() {

        Comment comment = null;

        for(int i=1; i <= 5; i++){

            comment = new Comment();
            comment.setCommentId(i);
            comment.setTitle("Test title #" + i);

            comments.add(comment);
        }
    }

    @Test
    public void testGetCommentsByEntityId() throws Exception {

        List<Comment> resultComments = commentArticleService.getCommentsByEntityId(1);

        Assert.assertTrue(!resultComments.isEmpty());
    }
}