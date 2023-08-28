package me.kacper;

import me.kacper.family.manager.FamilyManager;
import me.kacper.mongo.MongoHandler;
import me.kacper.program.Program;
import me.kacper.user.manager.UserManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static MongoHandler mongoHandler;
    private static UserManager userManager;
    private static FamilyManager familyManager;

    public static void main(String[] args) {
        mongoHandler = new MongoHandler("mongodb://158.69.163.121:42741/");

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        userManager = new UserManager(mongoHandler);
        familyManager = new FamilyManager(mongoHandler);

        new Program(mongoHandler, userManager, familyManager).run();
    }

    public static MongoHandler getMongoHandler() {
        return mongoHandler;
    }

    public static FamilyManager getFamilyManager() {
        return familyManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }
}