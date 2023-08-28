package me.kacper.user.manager;

import com.mongodb.client.model.Filters;
import me.kacper.helper.UserHelper;
import me.kacper.mongo.MongoHandler;
import me.kacper.user.User;
import org.bson.Document;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Consumer;

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

    @Deprecated
    public void getDataByPurchase(){
        System.out.println("Client Knowledge");
        mongoHandler.getUsers().find().forEach((Consumer<? super Document>) document -> {
            LocalDate today = LocalDate.now();
            LocalDate custom = LocalDate.of(document.getDate("expiry").getYear(), document.getDate("expiry").getMonth(), document.getDate("expiry").getDay());
            Period diff = Period.between(custom, today);
            System.out.println(document.getString("email") + ":" + diff.getMonths() + " Months");
        });
    }
}
