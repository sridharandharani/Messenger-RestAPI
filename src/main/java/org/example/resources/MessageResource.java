package org.example.resources;

import org.apache.log4j.Logger;
import org.example.model.Message;
import org.example.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class);

    MessageService messageService = new MessageService();

    public MessageResource() {
    }

    //    Get all messages
    @GET
    public List<Message>getMessages(){
        LOGGER.info("Inside Get messages");
        return messageService.getAllMessages();
    }

    //    Get a message with ID
    @GET
    @Path("/{messageId}")
    public List<Message> getMessage(@PathParam("messageId")long id){
        LOGGER.info("Inside Get messages using id");
        return messageService.getMessage(id);
    }


//    Post a new message
    @POST
    public Message addMessage(Message message){
        LOGGER.info("Inside Add messages");
        return messageService.addMessage(message);
    }


//    Update a message
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")long id, Message message){
        LOGGER.info("Inside Update messages");
        message.setId(id);
        return messageService.updateMessage(message);
    }


//    Delete a message
    @DELETE
    @Path("/{messageId}")
    public Response deleteMessage(@PathParam("messageId")long id){
        LOGGER.info("Inside Delete Message");
        messageService.removeMessage(id);
        return Response.noContent().build();
    }

}
