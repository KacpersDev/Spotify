package me.kacper.user.manager;

import com.mongodb.client.model.Filters;
import me.kacper.family.manager.FamilyManager;
import me.kacper.helper.UserHelper;
import me.kacper.mongo.MongoHandler;
import me.kacper.user.User;
import org.bson.Document;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
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
        if (document != null) return new User(document.getString("email"), document.getString("password"), document.getLong("expiry"), document.getString("owner"));
        return null;
    }

    @Deprecated
    public void getDataByPurchase(){
        System.out.println("Client Knowledge:");
        mongoHandler.getUsers().find().forEach((Consumer<? super Document>) document -> {
            long monthsBetween = ChronoUnit.MONTHS.between(
                    YearMonth.from(LocalDate.ofEpochDay(document.getLong("expiry"))),
                    YearMonth.from(LocalDate.ofEpochDay(FamilyManager.getPeriodOfOwner(document.getString("email"), mongoHandler)))
            );
            System.out.println(document.getString("email") + ":" + monthsBetween + " months to expire");
        });
    }
}
