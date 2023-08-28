package me.kacper;

import me.kacper.family.manager.FamilyManager;
import me.kacper.mongo.MongoHandler;
import me.kacper.program.Program;
import me.kacper.user.manager.UserManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        MongoHandler mongoHandler = new MongoHandler("mongodb://158.69.163.121:42741/");

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        UserManager userManager = new UserManager(mongoHandler);
        FamilyManager familyManager = new FamilyManager(mongoHandler);

        new Program(mongoHandler, userManager, familyManager).run();
    }
}
// 85 IS THE MONTH VALUE BERWEEN OWNER DATE AND USER DATE SO OWNER ACCOUNT WOULD EXPIRE FROM SPOTIFY IN 85 MONTHS.