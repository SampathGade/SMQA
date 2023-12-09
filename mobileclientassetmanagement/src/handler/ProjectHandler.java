package handler;

import dbmanager.DataManager;
import entity.project.*;
import entity.useraccount.User;
import util.AccessUtil;
import util.AppUtil;
import util.Constants;
import util.exports.ExportFactory;
import util.imports.ImportFactory;


import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class ProjectHandler implements Handler{
    private Map<Integer, String> projectHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public ProjectHandler() {
        this(null);
    }

    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }

    public ProjectHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            projectHandlerMap = AccessUtil.ADMIN_PROJECT_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            projectHandlerMap = AccessUtil.ASSET_MANAGER_PROJECT_MODULE_MAP;
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
                if(!projectHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(projectHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(projectHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(projectHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(projectHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(projectHandlerMap.get(option).startsWith("Import")) {
                    handleImport(scanner);
                }
                else if(projectHandlerMap.get(option).startsWith("Export")) {
                    handleExport();
                }
                else if(projectHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); return;}
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : projectHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Project Creation....");
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        Project project = new Project();
        handleFieldInput(project);
        project.setProjectID(ProjectUtil.generateProjectID());
        Integer projectID = project.getProjectID();
        ProjectInterface projectInterface = new ProjectFactoryImpl().createProject();
        projectInterface.add(project);
        System.out.println("Project Created Successfully!!!!");
        displayProjectDetailsVertical(projectDataMap.get(projectID));
    }

    private Project handleRetrieve() {
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        displayAllProjects();
        int providedID = scanner.nextInt();
        Project providedProject = projectDataMap.get(providedID);
        System.out.println("You have chosen the following Project");
        displayProjectDetailsVertical(providedProject);
        return providedProject;
    }

    private void handleUpdate() {
        Project projectToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = ProjectUtil.getTestInputForUpdate();
        }
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        handleFieldInput(projectToBeUpdated);
        Integer projectID = projectToBeUpdated.getProjectID();
        ProjectInterface projectInterface = new ProjectFactoryImpl().createProject();
        projectInterface.update(projectID, projectToBeUpdated);
        System.out.println("Project Updated Successfully!!!!");
        displayProjectDetailsVertical(projectDataMap.get(projectID));
    }

    private void handleDelete() {
        Project projectToBeDeleted = handleRetrieve();
        Integer projectID = projectToBeDeleted.getProjectID();
        ProjectInterface projectInterface = new ProjectFactoryImpl().createProject();
        projectInterface.delete(projectID);
        System.out.println("Project Deleted Successfully!!!!");
    }

    private void displayAllProjects() {
        System.out.println("Displaying All Projects");
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        for(Map.Entry<Integer, Project> projectEntry : projectDataMap.entrySet()){
            int projectID = projectEntry.getKey();
            Project project = projectEntry.getValue();
            System.out.println(projectID);
            displayProjectDetailsHorizontal(project);
        }
    }

    private void handleFieldInput(Project project) {
        try {
            Field[] projectFields = Project.class.getDeclaredFields();
            for(Field field : projectFields) {
                if(ProjectUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == User.class) {
                    Map<Integer, User> userDataMap = DataManager.getUserData();
                    System.out.println("Displaying All Users");
                    for(User user:userDataMap.values()) {
                        System.out.println(user.getUserID() + "." + user.getName());
                    }
                    System.out.println("Choose User");
                    Integer userID = scanner.nextInt();
                    User selectedUser = userDataMap.get(userID);
                    field.set(project, selectedUser);
                }
                else{
                    String input = scanner.nextLine();
                    field.set(project, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input");}
    }

    private void handleImport(Scanner scanner) {
        System.out.println("Please provide Absolute Path to Import");
        scanner.nextLine();
        String filePath = scanner.nextLine();
        ImportFactory.getHandler(Constants.PROJECT).handleImport(filePath);
        System.out.println("Import Successfully Completed");
    }

    private void handleExport() {
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        ExportFactory.getHandler(Constants.PROJECT).handleExport(projectDataMap, "");
        System.out.println("Export Completed");
    }

    private static void displayProjectDetailsVertical(Project project) {
        System.out.println("\nProject details:");
        System.out.println("ProjectID: " +project.getProjectID());
        System.out.println("ProjectName: " +project.getProjectName());
        System.out.println("ProjectDescription: " +project.getProjectDescription());
        System.out.println("ProjectOwner: " +project.getProjectOwner().getName());
    }

    private static void displayProjectDetailsHorizontal(Project project) {
        System.out.print("ProjectID: " +project.getProjectID()+", ");
        System.out.print("ProjectName: " +project.getProjectName()+", ");
        System.out.print("ProjectDescription: " +project.getProjectDescription()+", ");
        System.out.print("ProjectOwner: " +project.getProjectOwner().getName());
    }
}
