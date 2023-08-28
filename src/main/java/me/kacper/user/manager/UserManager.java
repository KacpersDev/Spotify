package me.kacper.user.manager;

import com.mongodb.client.model.Filters;
import me.kacper.family.manager.FamilyManager;
import me.kacper.helper.UserHelper;
import me.kacper.mongo.MongoHandler;
import me.kacper.user.User;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
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
        if (document != null) return new User(document.getString("email"), document.getString("password"), document.getLong("expiry"), document.getString("owner"), document.getInteger("plan"));
        return null;
    }

    @Deprecated
    public void getDataByPurchase(){
        System.out.println("Client Knowledge:");
        Map<String, Integer> months = new HashMap<>();
        mongoHandler.getUsers().find().forEach((Consumer<? super Document>) document -> {
            if (document.getInteger("plan") == 3) {
                if (months.get("3") == null) {
                    months.put("3", 1);
                } else {
                    months.replace("3", months.get("3"), months.get("3") + 1);
                }
            } else if (document.getInteger("plan") == 6) {
                if (months.get("6") == null) {
                    months.put("6", 1);
                } else {
                    months.replace("6", months.get("6"), months.get("6") + 1);
                }
            } else if (document.getInteger("plan") == 12) {
                if (months.get("12") == null) {
                    months.put("12", 1);
                } else {
                    months.replace("12", months.get("12"), months.get("12") + 1);
                }
            }

            months.putIfAbsent("3", 0);
            months.putIfAbsent("6", 0);
            months.putIfAbsent("12", 0);

            System.out.println("3 Months: " + months.get("3"));
            System.out.println("6 Months: " + months.get("6"));
            System.out.println("12 Months: " + months.get("12"));
        });
    }
}
