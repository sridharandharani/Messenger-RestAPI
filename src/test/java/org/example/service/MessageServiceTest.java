package org.example.service;

import org.example.model.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Map;


public class MessageServiceTest {

    @Mock
    private Map<Long, Message> messages;

    @InjectMocks
    private MessageService messageService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMessages(){
        when(messages.values()).thenReturn(new ArrayList<Message>());
        ArrayList<Message> result = messageService.getAllMessages();
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetMessage(){
        Message message = new Message();
        message.setId(1L);
        when(messages.get(1L)).thenReturn(message);
        ArrayList<Message> result = messageService.getMessage(1L);
        Assert.assertNotNull(result);
    }

    @Test
    public void testAddMessage(){
        Message message = new Message();
        message.setMessage("Test Message");
        message.setAuthor("Test Author");
        Message result = messageService.addMessage(message);
        Assert.assertEquals(result.getMessage(),message.getMessage());
        Assert.assertEquals(result.getAuthor(),message.getAuthor());
    }

    @Test
    public void testUpdateMessage(){
        Message message = new Message();
        message.setId(1L);
        message.setMessage("Test Message");
        message.setAuthor("Test Author");
        when(messages.get(1L)).thenReturn(message);
        Message result = messageService.updateMessage(message);
        Assert.assertEquals(result.getMessage(),message.getMessage());
        Assert.assertEquals(result.getAuthor(),message.getAuthor());
    }

    @Test
    public void testRemoveMessage(){
        Message message = new Message();
        message.setId(1L);
        message.setMessage("Test Message");
        message.setAuthor("Test Author");
        when(messages.remove(1L)).thenReturn(message);
        Message result = messageService.removeMessage(1L);
        Assert.assertEquals(result,message);
    }

}