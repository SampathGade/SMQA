package mobileclientassetmanagement.src.handler;


import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.util.AppUtil;

public class ProfileHandler implements Handler {
    @Override
    public void execute() {
        User currentUser = AppUtil.getCurrentUser();
        displayAboutMeDetailsVertical(currentUser);
    }

    private static void displayAboutMeDetailsVertical(User user) {
        System.out.println("\n About Me");
        System.out.println("UserID: " +user.getUserID());
        System.out.println("Name: " +user.getName());
        System.out.println("EmailID: " +user.getEmailID());
        System.out.println("UserRole: " +user.getUserRole());
        System.out.println("JobTitle: " +user.getJobTitle());
        System.out.println("Description: " +user.getDescription());
    }

}
