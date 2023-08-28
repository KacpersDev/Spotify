package me.kacper.program;

import me.kacper.family.Family;
import me.kacper.family.manager.FamilyManager;
import me.kacper.helper.FamilyHelper;
import me.kacper.helper.UserHelper;
import me.kacper.mongo.MongoHandler;
import me.kacper.user.User;
import me.kacper.user.manager.UserManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Program {

    private final UserManager userManager;
    private final FamilyManager familyManager;
    private final MongoHandler mongoHandler;

    public Program(MongoHandler mongoHandler, UserManager userManager, FamilyManager familyManager) {
        this.mongoHandler = mongoHandler;
        this.userManager = userManager;
        this.familyManager = familyManager;
    }

    public void run(){
        Scanner actionScanner;
        String action;

        do {
            System.out.println("Select an option");
            System.out.println(" ");
            System.out.println("1 - Add User Account");
            System.out.println("2 - Remove User Account");
            System.out.println("3 - Add Family Account");
            System.out.println("4 - Remove Family Account");
            System.out.println("5 - Family Account Info");
            System.out.println("6 - List Family Accounts");
            System.out.println("7 - Client Info");
            System.out.println("8 - Client Knowledge");
            System.out.println("9 - Exit");
            System.out.println(" ");
            System.out.println("Action: ");
            actionScanner = new Scanner(System.in);
            action = actionScanner.nextLine();

            if (action.equalsIgnoreCase("1")) {
                System.out.println("Email: ");
                Scanner emailScanner = new Scanner(System.in);
                String email = emailScanner.nextLine();
                System.out.println("Password: ");
                Scanner passwordScanner = new Scanner(System.in);
                String password = passwordScanner.nextLine();
                System.out.println("Expiry FORMAT (yyyy-MM-dd): ");
                Scanner expiryScanner = new Scanner(System.in);
                String expiry = expiryScanner.nextLine();
                System.out.println("Owner Email: ");
                Scanner ownerScanner = new Scanner(System.in);
                String owner = ownerScanner.nextLine();

                mongoHandler.getUsers().insertOne(UserHelper.to(new User(email, password, LocalDate.of(Integer.parseInt(expiry.split("-")[0]),Integer.parseInt(expiry.split("-")[1]),Integer.parseInt(expiry.split("-")[2])).toEpochDay(), owner)));
                System.out.println("Successfully created user.");
            } else if (action.equalsIgnoreCase("2")) {
                System.out.println("Email: ");
                Scanner emailScanner = new Scanner(System.in);
                String email = emailScanner.nextLine();

                this.userManager.removeUser(this.userManager.getUserByEmail(email));
                System.out.println("Successfully deleted user.");
            } else if (action.equalsIgnoreCase("3")) {
                System.out.println("Email: ");
                Scanner emailScanner = new Scanner(System.in);
                String email = emailScanner.nextLine();
                System.out.println("Password: ");
                Scanner passwordScanner = new Scanner(System.in);
                String password = passwordScanner.nextLine();
                System.out.println("Expiry Date FORMAT (yyyy-MM-dd): ");
                Scanner dateScanner = new Scanner(System.in);
                String expiry = dateScanner.nextLine();
                System.out.println("Invite link: ");
                Scanner inviteScanner = new Scanner(System.in);
                String invite = inviteScanner.nextLine();
                System.out.println("Address: ");
                Scanner addressScanner = new Scanner(System.in);
                String address = addressScanner.nextLine();
                mongoHandler.getFamilies().insertOne(FamilyHelper.to(new Family(email, password, LocalDate.of(Integer.parseInt(expiry.split("-")[0]),Integer.parseInt(expiry.split("-")[1]),Integer.parseInt(expiry.split("-")[2])).toEpochDay(), invite, address)));
                System.out.println("Created family user account.");
            } else if (action.equalsIgnoreCase("4")) {
                System.out.println("Email: ");
                Scanner emailScanner = new Scanner(System.in);
                String email = emailScanner.nextLine();

                this.familyManager.removeUser(this.familyManager.getUserByEmail(email));
                System.out.println("Successfully removed family user.");
            } else if (action.equalsIgnoreCase("5")) {
                System.out.println("Email: ");
                Scanner emailScanner = new Scanner(System.in);
                String email = emailScanner.nextLine();

                Family family = this.familyManager.getUserByEmail(email);

                System.out.println(family.getEmail() + " Data");
                System.out.println(" ");
                System.out.println("Email: " + family.getEmail());
                System.out.println("Password: " + family.getPassword());
                System.out.println("Address: " + family.getAddress());
                System.out.println("Expiry: " + family.getExpiry());
                System.out.println("Link: " + family.getInviteLink());
                System.out.println(" ");
            } else if (action.equalsIgnoreCase("6")) {
                System.out.println(" ");
                System.out.println("List of family accounts emails: ");
                this.familyManager.getUsers();
            } else if (action.equalsIgnoreCase("9")) {
                return;
            } else if (action.equalsIgnoreCase("7")) {
                System.out.println("Email: ");
                Scanner emailScanner = new Scanner(System.in);
                String email = emailScanner.nextLine();

                User user = this.userManager.getUserByEmail(email);

                System.out.println(user.getEmail() + " Data");
                System.out.println(" ");
                System.out.println("Email: " + user.getEmail());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Owner: " + user.getOwnerEmail());
                System.out.println("Expiry: " + user.getExpiry());
                System.out.println(" ");
            } else if (action.equalsIgnoreCase("8")) {
                this.userManager.getDataByPurchase();
            }
        } while (!action.equalsIgnoreCase("1") || !action.equalsIgnoreCase("2") || !action.equalsIgnoreCase("3") || !action.equalsIgnoreCase("4"));
    }
}
