package org.example.resources;

import org.example.model.Message;
import org.example.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    public MessageResource() {
    }

    //    Get all messages
    @GET
    public List<Message>getMessages(){
        return messageService.getAllMessages();
    }

    //    Get a message with ID
    @GET
    @Path("/{messageId}")
    public List<Message> getMessage(@PathParam("messageId")long id){
        return messageService.getMessage(id);
    }


//    Post a new message
    @POST
    public Message addMessage(Message message){
        return messageService.addMessage(message);
    }


//    Update a message
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")long id, Message message){
        message.setId(id);
        return messageService.updateMessage(message);
    }


//    Delete a message
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId")long id){
        messageService.removeMessage(id);
    }

}
