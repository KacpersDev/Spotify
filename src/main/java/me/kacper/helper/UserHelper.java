package me.kacper.helper;

import me.kacper.user.User;
import org.bson.Document;

public class UserHelper {

    public static Document to(User user){
        Document document = new Document();
        document.put("email", user.getEmail());
        document.put("password", user.getPassword());
        document.put("owner", user.getOwnerEmail());
        document.put("expiry", user.getExpiry());
        return document;
    }
}
