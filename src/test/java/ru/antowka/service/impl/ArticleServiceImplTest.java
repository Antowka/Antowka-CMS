package ru.antowka.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.antowka.dao.ArticleDao;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;

/**
 * Tests for ArticleService
 */
@RunWith(PowerMockRunner.class)
public class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleDao articleDao;

    @Mock
    private UserDao userDao;

    @Mock
    private org.springframework.security.core.userdetails.User userDetail;

    @Mock
    private User user;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    /**
     * Check creating new article with get user auth context
     *
     * @throws Exception
     */
    @Test
    @PrepareForTest({SecurityContextHolder.class})
    public void testCreateArticle() throws Exception {

        Article article = new Article();

        PowerMockito.mockStatic(SecurityContextHolder.class);

        PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetail);

        Mockito.when(userDetail.getUsername()).thenReturn("userName");
        Mockito.when(userDao.findByUserName("userName")).thenReturn(user);

        articleService.createArticle(article);

        ArgumentCaptor<Article> argument = ArgumentCaptor.forClass(Article.class);
        Mockito.verify(articleDao, Mockito.times(1)).createArticle(argument.capture());

        Assert.assertEquals(argument.getValue().getUserOwner(), user);
    }

    @Test
    public void testUpdateArticle() throws Exception {

        Article article = new Article();
        article.setTitle("test");

        Mockito.when(articleDao.updateArticle(article)).thenReturn(article);

        Article articleResult = articleService.updateArticle(article);

        Assert.assertEquals(articleResult, article);
    }

    @Test
    public void testRemoveArticle() throws Exception {

        MessageResponseServiceImpl mrs = new MessageResponseServiceImpl();

        //Successful case
        mrs.setMessageResponse(new MessageResponse());
        articleService.setMessageResponseService(mrs);

        Article articleSuccess = new Article();
        articleSuccess.setTitle("test success");
        articleSuccess.setDeleted(true);

        Mockito.when(articleDao.removeArticle(articleSuccess)).thenReturn(articleSuccess);

        MessageResponse messageSuccess = articleService.removeArticle(articleSuccess);


        //Fail case
        mrs.setMessageResponse(new MessageResponse());
        articleService.setMessageResponseService(mrs);

        Article articleFail = new Article();
        articleFail.setTitle("test fail");
        articleFail.setDeleted(false);

        Mockito.when(articleDao.removeArticle(articleFail)).thenReturn(articleFail);

        MessageResponse messageFail = articleService.removeArticle(articleFail);

        Assert.assertTrue(messageSuccess.getCode() == 1 && messageFail.getCode() == 0);
    }

    @Test
    public void testGetArticles() throws Exception {

    }

    @Test
    public void testGetArticle() throws Exception {

    }

    @Test
    public void testGetArticlesByUser() throws Exception {

    }
}