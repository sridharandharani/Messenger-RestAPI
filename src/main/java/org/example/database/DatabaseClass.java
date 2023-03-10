package org.example.database;


import org.apache.log4j.Logger;
import org.example.model.Message;
import org.example.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static final Logger LOGGER = Logger.getLogger(DatabaseClass.class);
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<Long, Profile> profiles = new HashMap<>();

    public DatabaseClass(){
    }

    public static Map<Long, Message> getMessages(){
        LOGGER.info("Inside getMessages");
        return messages;
    }

    public static Map<Long, Profile> getProfiles(){
        LOGGER.info("Inside getMessages");
        return profiles;
    }

}
