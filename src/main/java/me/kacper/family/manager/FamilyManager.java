package me.kacper.family.manager;

import com.mongodb.client.model.Filters;
import me.kacper.family.Family;
import me.kacper.helper.FamilyHelper;
import me.kacper.mongo.MongoHandler;
import org.bson.Document;

public class FamilyManager {

    private final MongoHandler mongoHandler;

    public FamilyManager(MongoHandler mongoHandler) {
        this.mongoHandler = mongoHandler;
    }

    public void createUser(Family family){
        mongoHandler.getFamilies().insertOne(FamilyHelper.to(family));
    }

    public void removeUser(Family family) {
        mongoHandler.getFamilies().deleteOne(FamilyHelper.to(family));
    }

    public Family getUserByEmail(String email) {
        Document document = mongoHandler.getFamilies().find(Filters.eq("email", email)).first();
        if (document != null) return new Family(document.getString("email"), document.getString("password"),
                document.getDate("expiry"), document.getString("link"), document.getString("address"));
        return null;
    }

    public void getUsers() {
        System.out.println("Email " + mongoHandler.getFamilies().find().cursor().next().getString("email"));
    }
}
