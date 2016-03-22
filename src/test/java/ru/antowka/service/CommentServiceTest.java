package ru.antowka.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.dao.CommentDao;
import ru.antowka.entity.Comment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;
import ru.antowka.service.impl.CommentArticleService;

import static org.junit.Assert.*;

/**
 * Test check CommentService
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    CommentArticleService commentService;

    @Mock
    CommentDao commentDao;

    @Mock
    UserService userServices;

    @Mock
    MessageResponse messageResponse;

    User user;

    Comment comment;

    @Before
    public void setUp() {

        //Create demo user
        user = new User();
        user.setEnable(true);
        user.setUserId(1);
        user.setFirstName("Anton");
        user.setLogin("anton");
        user.setEmail("test@example.ru");

        //Create demo comment
        comment = new Comment();
        comment.setCommentId(0);
        comment.setTitle("Test title");
        comment.setDescription("Demo comment text");
        comment.setIsDeleted(false);
    }

    @Test
    public void testCreateComment() throws Exception {

        Mockito.when(userServices.getAuthorizedUser()).thenReturn(user);
        Mockito.when(commentDao.createComment(comment)).thenReturn(comment);

        Comment commentResult = commentService.createComment(comment);

        Assert.assertTrue(commentResult.getUser().getUserId() == 1 && commentResult.getCommentId() == 0);
    }

    @Test
    public void testRemoveComment() throws Exception {

        comment.setIsDeleted(true);
        commentService.setMessageResponse(new MessageResponse());

        Mockito.when(commentDao.removeComment(1)).thenReturn(comment);

        MessageResponse messageResponse = commentService.removeComment(1);

        //Back default values
        comment.setIsDeleted(false);

        Assert.assertTrue(messageResponse.getCode() == 1);
    }

    @Test
    public void testUpdateComment() throws Exception {

        String defaultTitle = comment.getTitle();
        comment.setTitle("new Title after update");

        Mockito.when(commentDao.updateComment(comment)).thenReturn(comment);

        Assert.assertNotEquals(comment.getTitle(), defaultTitle);

        comment.setTitle(defaultTitle);
    }
}