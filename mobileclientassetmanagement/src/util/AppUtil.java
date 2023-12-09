package mobileclientassetmanagement.src.util;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.role.UserRole;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.entity.useraccount.UserFactoryImpl;
import mobileclientassetmanagement.src.entity.useraccount.UserInterface;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.HandlerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class AppUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static User user;

    private static Boolean isFromTest = false;

    public static void setCurrentUser(User currentUser) {
        user = currentUser;
    }

    public static User getCurrentUser(){
        return user;
    }

    public static void setIsFromTest(Boolean fromTest) {
        isFromTest = fromTest;
    }

    public static Boolean isFromTest() {
        return isFromTest;
    }

    public static String getDesktopPath() {
        String userHome = System.getProperty("user.home");
        if(userHome != null) {
            return userHome + "/Desktop";
        }
        return null;
    }

    public static void createFolder(String folderPath) {
        Path filePath = Paths.get(folderPath);
        if(Files.exists(filePath)){
            return;
        }
        try { Files.createDirectories(filePath);} catch (Exception e) { System.out.println("Folder Not Created"); }
    }

    public static User getCurrentUser(String userName, String userEmail) {
        Map<Integer, User> userData = DataManager.getUserData(); if(userData.isEmpty()){ return null; } for(User user : userData.values()) { if(user.getName().equals(userName) && user.getEmailID().equals(userEmail)) { return user;}} return null;
    }

    public static Boolean isValidUser(String userName, String userEmail) throws Exception {User user = getCurrentUser(userName, userEmail); if(user == null) { return false;} return true;}

    public static void handleSignUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SignUp");
        System.out.println();
        System.out.println("New User Creation....");
        System.out.println("Enter your Name");
        String userName = scanner.next();
        System.out.println("Enter your EmailID");
        String userEmail = scanner.next();
        System.out.println("Enter your JobTitle");
        String userJobTitle = scanner.next();
        User user = new User(UserUtil.generateUserID(), userName, userEmail, UserRole.ADMIN, userJobTitle, "");
        UserInterface userInterface = new UserFactoryImpl().createUser();
        userInterface.add(user);
        System.out.println("User Created Successfully!!!!");
        handleLoginView();
    }
    public static void handleLoginView() {
        if(AppUtil.isFromTest()) {return;}
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Login");
            System.out.println();
            System.out.println("Enter your Name");
            String userName = scanner.nextLine();
            System.out.println("Enter your EmailID");
            String userEmail = scanner.nextLine();
            Boolean isValidUser = AppUtil.isValidUser(userName, userEmail);
            if(!isValidUser) { System.out.println("User doesn't exists!, Please Contact Admin."); return; }
            User currentUser = AppUtil.getCurrentUser(userName, userEmail);
            AppUtil.setCurrentUser(currentUser);
            System.out.println("User Login Successful...");
            HandlerFactory.instantiateHandler("ModuleHandler").execute();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
