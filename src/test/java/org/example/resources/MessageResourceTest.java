package org.example.resources;

import org.example.model.Message;
import org.example.service.MessageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;



import java.util.ArrayList;
import java.util.List;


public class MessageResourceTest {
    @Mock
    private MessageService messageService;

    @Mock
    private MessageResource messageResource;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        messageResource = new MessageResource();
        messageResource.messageService = messageService;
    }

    @Test
    public void testGetMessages(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1,"HELLO","NAME"));
        messages.add(new Message(2,"WORLD","NAME"));
        when(messageService.getAllMessages()).thenReturn((ArrayList<Message>) messages);
        List<Message> response = messageResource.getMessages();
        verify(messageService,Mockito.times(1)).getAllMessages();
        Assert.assertEquals(messages,response);
    }

    @Test
    public void testGetMessage(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1,"HELLO","NAME"));
        when(messageService.getMessage(1L)).thenReturn((ArrayList<Message>) messages);
        List<Message> response = messageResource.getMessage(1L);
        verify(messageService,Mockito.times(1)).getMessage(1L);
        Assert.assertEquals(messages,response);
    }

    @Test
    public void testAddMessage(){
        Message message = new Message(1,"Hello","name");
        when(messageService.addMessage(message)).thenReturn(message);
        Message response = messageResource.addMessage(message);
        verify(messageService,Mockito.times(1)).addMessage(message);
        Assert.assertEquals(message,response);
    }

    @Test
    public void testUpdateMessage(){
        Message message = new Message(1,"Hello","name");
        when(messageService.updateMessage(message)).thenReturn(message);
        Message response = messageResource.updateMessage(1,message);
        verify(messageService,times(1)).updateMessage(message);
        Assert.assertEquals(message,response);
    }

    @Test
    public void testDeleteMessage(){
        Response response = messageResource.deleteMessage(1L);
        verify(messageService,times(1)).removeMessage(1);
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(),response.getStatus());
    }


}