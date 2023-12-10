package mobileclientassetmanagement.src.handler;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.role.UserRole;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.entity.useraccount.UserFactoryImpl;
import mobileclientassetmanagement.src.entity.useraccount.UserInterface;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import mobileclientassetmanagement.src.util.exports.ExportFactory;
import mobileclientassetmanagement.src.util.imports.ImportFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class UserHandler implements Handler
{
    private Map<Integer, String> userHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }
    public UserHandler() {
        this(null);
    }
    public UserHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            userHandlerMap = AccessUtil.ADMIN_USER_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            userHandlerMap = AccessUtil.ASSET_MANAGER_USER_MODULE_MAP;
        }
        this.scanner = scanner == null ? new Scanner(System.in) : scanner;
    }

    @Override
    public void execute() {
        do {
            try {
                System.out.println("\n");
                showOperations();
                int option = !AppUtil.isFromTest() ? scanner.nextInt() : Integer.parseInt(scanner.nextLine().trim());
                if(!userHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(userHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(userHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(userHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(userHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(userHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer");}
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : userHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New User Creation....");
        Map<Integer, User> userDataMap = DataManager.getUserData();
        User user = new User();
        handleFieldInput(user);
        user.setUserID(UserUtil.generateUserID());
        Integer userID = user.getUserID();
        UserInterface userInterface = new UserFactoryImpl().createUser();
        userInterface.add(user);
        System.out.println("User Created Successfully!!!!");
        displayUserDetailsVertical(userDataMap.get(userID));
    }

    private User handleRetrieve() {
        displayAllUsers();
        Map<Integer, User> userDataMap = DataManager.getUserData();
        int providedID = scanner.nextInt();
        User providedUser = userDataMap.get(providedID);
        System.out.println("You have chosen the following User");
        displayUserDetailsVertical(providedUser);
        return providedUser;
    }

    private void handleUpdate() {
        User userToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = UserUtil.getTestInputForUpdate();
        }
        Map<Integer, User> userDataMap = DataManager.getUserData();
        handleFieldInput(userToBeUpdated);
        Integer userID = userToBeUpdated.getUserID();
        UserInterface userInterface = new UserFactoryImpl().createUser();
        userInterface.update(userID, userToBeUpdated);
        System.out.println("User Updated Successfully!!!!");
        displayUserDetailsVertical(userDataMap.get(userID));
    }

    private void handleDelete() {
        User userToBeDeleted = handleRetrieve();
        Integer userID = userToBeDeleted.getUserID();
        UserInterface userInterface = new UserFactoryImpl().createUser();
        userInterface.delete(userID);
        System.out.println("User Deleted Successfully!!!!");
    }

    private void displayAllUsers() {
        System.out.println("Displaying All Users:");
        Map<Integer, User> userDataMap = DataManager.getUserData();
        for(Map.Entry<Integer, User> userEntry : userDataMap.entrySet()){
            int userID = userEntry.getKey();
            User user = userEntry.getValue();
            System.out.println(userID);
            displayUserDetailsHorizontal(user);
        }
    }

    private void handleFieldInput(User user) {
        try {
            Field[] userFields = User.class.getDeclaredFields();
            for(Field field : userFields) {
                if(UserUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == UserRole.class) {
                    System.out.println("Valid UserRoles: ");
                    for(UserRole userRole : UserRole.values()) {
                        System.out.println(userRole.getCode() + "." + userRole.getRoleName());
                    }
                    System.out.println("choose Role Number");
                    int roleNumber = scanner.nextInt();
                    UserRole selecteduserRole = UserRole.getRoleName(roleNumber);
                    field.set(user, selecteduserRole);
                }
                else{
                    String input = scanner.next();
                    field.set(user, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input");}
    }
    
    private void handleImport(Scanner scanner) {
        System.out.println("Please provide Absolute Path to Import");
        scanner.nextLine();
        String filePath = scanner.nextLine();
        ImportFactory.getHandler(Constants.USER).handleImport(filePath);
        System.out.println("Import Successfully Completed");
    }

    private void handleExport() {
        Map<Integer, User> assetDataMap = DataManager.getUserData();
        ExportFactory.getHandler(Constants.USER).handleExport(assetDataMap, "");
    }
    
    private static void displayUserDetailsVertical(User user) {
        System.out.println("\nUser details:");
        System.out.println("UserID: " +user.getUserID());
        System.out.println("Name: " +user.getName());
        System.out.println("EmailID: " +user.getEmailID());
        System.out.println("UserRole: " +user.getUserRole());
        System.out.println("JobTitle: " +user.getJobTitle());
        System.out.println("Description: " +user.getDescription());
    }

    private static void displayUserDetailsHorizontal(User user) {
        System.out.print("UserID: " + user.getUserID()+", ");
        System.out.print("Name: " + user.getName()+", ");
        System.out.print("EmailID: " + user.getEmailID()+", ");
        System.out.print("UserRole: " + user.getUserRole()+", ");
        System.out.print("JobTitle: " + user.getJobTitle()+", ");
        System.out.print("Description: " + user.getDescription());
    }
}
