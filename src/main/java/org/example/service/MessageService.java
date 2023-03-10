package org.example.service;

import org.apache.log4j.Logger;
import org.example.database.DatabaseClass;
import org.example.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class);

    Connection connection;

    private Map <Long,Message> messages = DatabaseClass.getMessages();

    public MessageService() {

        // Connecting to Database
        LOGGER.info("Connecting to Database");
        try {
            String url = String.format("jdbc:mysql://localhost:3306/messenger_db");
            String uname = "root";
            String pwd = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,uname,pwd);

        }catch (Exception e){
            LOGGER.error( e.getMessage());
        }

    }

    // Getting All messages from Database
    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> data = new ArrayList<Message>();
        LOGGER.info("Inside get All Messages");
        String view = "SELECT * FROM messages";
        try {
            PreparedStatement ps = connection.prepareStatement(view);
            ResultSet rs =  ps.executeQuery();

            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setMessage(rs.getString("Message"));
                message.setAuthor(rs.getString("Author"));
                message.setCreated(rs.getDate("Date"));

                data.add(message);
            }
            return data;

        }catch (Exception e){
            LOGGER.error( e.getMessage());
        }
        LOGGER.info("Exiting the get all Messages");
        return new ArrayList<Message>(messages.values());
    }

//    To get a message by ID
    public ArrayList<Message> getMessage(Long id) {
        ArrayList<Message> data = new ArrayList<Message>();
        LOGGER.info("Inside get Messages using id");
        String viewById = "SELECT * FROM messages where id = "+id+"";
        try {
            PreparedStatement ps = connection.prepareStatement(viewById);
            ResultSet rs =  ps.executeQuery();

            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setMessage(rs.getString("Message"));
                message.setAuthor(rs.getString("Author"));
                message.setCreated(rs.getDate("Date"));

                data.add(message);
            }
            return data;

        }catch (Exception e){
            LOGGER.error( e.getMessage());
        }
        LOGGER.info("Exiting the get Messages by id");
        return new ArrayList<Message>((Collection<? extends Message>) messages.get(id));
    }


//      To add a message
    public Message addMessage(Message message) {
        LOGGER.info("Inside Add Messages");
        String insert = "INSERT INTO messages(message,author) values(?,?) ";
        try {
            PreparedStatement ps = connection.prepareStatement(insert);
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAuthor());

            ps.execute();

        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        LOGGER.error("Exiting Add Messages");
        return message;
    }

//       To update message
    public Message updateMessage(Message message){
        LOGGER.info("Inside Update Messages");
        String update = "UPDATE messages SET message = ?,author = ? WHERE id ="+ message.getId()+" ";
        try {
            PreparedStatement ps = connection.prepareStatement(update);
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAuthor());

            ps.executeUpdate();

        }catch (Exception e){
            LOGGER.error( e.getMessage());
        }
        LOGGER.info("Exiting update Messages");
        return message;
    }

//         To remove message
public Message removeMessage(long id){
        LOGGER.info("Inside Delete Message");
    String delete = "DELETE FROM messages WHERE id ="+id+" ";
    try {
        PreparedStatement ps = connection.prepareStatement(delete);

        ps.executeUpdate();

    }catch (Exception e){
        LOGGER.error( e.getMessage());
    }
    LOGGER.info("Exiting Delete Message");
    return messages.remove(id);
}

}
