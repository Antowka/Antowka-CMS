package ru.antowka.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.antowka.entity.MessageResponse;
import ru.antowka.service.MessageResponseService;

import static org.junit.Assert.*;

/**
 * Test for MessageResponseService
 */
@RunWith(PowerMockRunner.class)
public class MessageResponseServiceImplTest {

    @InjectMocks
    private MessageResponseServiceImpl messageResponseService;


    @Test
    public void testGetResponseForRemoveEntity() throws Exception {

        messageResponseService.setMessageResponse(new MessageResponse());
        MessageResponse messageResponseSuccess = messageResponseService.getResponseForRemoveEntity(true, "Article", 1);

        messageResponseService.setMessageResponse(new MessageResponse());
        MessageResponse messageResponseFail = messageResponseService.getResponseForRemoveEntity(false, "Article", 1);

        Assert.assertTrue(messageResponseFail.getCode() == 0 && messageResponseSuccess.getCode() == 1);
    }

    @Test
    public void testGetResponseForUpdateEntity() throws Exception {

        messageResponseService.setMessageResponse(new MessageResponse());
        MessageResponse messageResponseSuccess = messageResponseService.getResponseForUpdateEntity(true, "Article", 1);

        messageResponseService.setMessageResponse(new MessageResponse());
        MessageResponse messageResponseFail = messageResponseService.getResponseForUpdateEntity(false, "Article", 1);

        Assert.assertTrue(messageResponseFail.getCode() == 0 && messageResponseSuccess.getCode() == 1);
    }

    @Test
    public void testGetResponseForCreateEntity() throws Exception {

        messageResponseService.setMessageResponse(new MessageResponse());
        MessageResponse messageResponseSuccess = messageResponseService.getResponseForCreateEntity(true, "Article", 1);

        messageResponseService.setMessageResponse(new MessageResponse());
        MessageResponse messageResponseFail = messageResponseService.getResponseForCreateEntity(false, "Article", 1);

        Assert.assertTrue(messageResponseFail.getCode() == 0 && messageResponseSuccess.getCode() == 1);
    }
}