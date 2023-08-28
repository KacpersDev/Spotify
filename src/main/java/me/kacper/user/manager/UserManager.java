package me.kacper.user.manager;

import com.mongodb.client.model.Filters;
import me.kacper.helper.UserHelper;
import me.kacper.mongo.MongoHandler;
import me.kacper.user.User;
import org.bson.Document;

public class UserManager {

    private final MongoHandler mongoHandler;

    public UserManager(MongoHandler mongoHandler) {
        this.mongoHandler = mongoHandler;
    }

    public void createUser(User user){
        mongoHandler.getUsers().insertOne(UserHelper.to(user));
    }

    public void removeUser(User user) {
        mongoHandler.getUsers().deleteOne(UserHelper.to(user));
    }

    public User getUserByEmail(String email) {
        Document document = mongoHandler.getUsers().find(Filters.eq("email", email)).first();
        if (document != null) return new User(document.getString("email"), document.getString("password"), document.getDate("expiry"), document.getString("owner"));
        return null;
    }
}
