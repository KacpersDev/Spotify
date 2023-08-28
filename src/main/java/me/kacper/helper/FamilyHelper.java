package me.kacper.helper;

import me.kacper.family.Family;
import org.bson.Document;

public class FamilyHelper {

    public static Document to(Family family){
        Document document = new Document();
        document.put("email", family.getEmail());
        document.put("password", family.getPassword());
        document.put("link", family.getInviteLink());
        document.put("address", family.getAddress());
        document.put("expiry", family.getExpiry());
        return document;
    }
}
