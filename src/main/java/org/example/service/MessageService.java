package org.example.service;

import org.example.database.DatabaseClass;
import org.example.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MessageService {

    Connection connection;

    private Map <Long,Message> messages = DatabaseClass.getMessages();

    public MessageService() {

        // Connecting to Database
        try {
            String url = String.format("jdbc:mysql://localhost:3306/messenger_db");
            String uname = "root";
            String pwd = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,uname,pwd);

        }catch (Exception e){
            System.out.println( e + "Connection failed");
        }

    }

    // Getting All messages from Database
    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> data = new ArrayList<Message>();
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
            System.out.println( e + "Get Message failed");
        }
        return new ArrayList<Message>(messages.values());
    }

//    To get a message by ID
    public ArrayList<Message> getMessage(Long id) {
        ArrayList<Message> data = new ArrayList<Message>();
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
            System.out.println( e + "Get Message by ID failed");
        }
        return new ArrayList<Message>((Collection<? extends Message>) messages.get(id));
    }


//      To add a message
    public Message addMessage(Message message) {
        String insert = "INSERT INTO messages(message,author) values(?,?) ";
        try {
            PreparedStatement ps = connection.prepareStatement(insert);
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAuthor());

            ps.execute();

        }catch (Exception e){
            System.out.println( e + "Inserting Message failed");
        }
        return message;
    }

//       To update message
    public Message updateMessage(Message message){
        String update = "UPDATE messages SET message = ?,author = ? WHERE id ="+ message.getId()+" ";
        try {
            PreparedStatement ps = connection.prepareStatement(update);
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAuthor());

            ps.executeUpdate();

        }catch (Exception e){
            System.out.println( e + "Updating Message failed");
        }
        return message;
    }

//         To remove message
public Message removeMessage(long id){
    String delete = "DELETE FROM messages WHERE id ="+id+" ";
    try {
        PreparedStatement ps = connection.prepareStatement(delete);

        ps.executeUpdate();

    }catch (Exception e){
        System.out.println( e + "Deleting Message failed");
    }

    return messages.remove(id);
}

}
