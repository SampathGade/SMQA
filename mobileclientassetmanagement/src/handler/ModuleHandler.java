package mobileclientassetmanagement.src.handler;


import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import java.util.Map;
import java.util.Scanner;

public class ModuleHandler implements Handler {
    private Map<Integer, String> moduleViewMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }

    public ModuleHandler() {
        this(null);
    }
    public ModuleHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            moduleViewMap = AccessUtil.ADMIN_VIEW_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            moduleViewMap = AccessUtil.ASSET_MANAGER_VIEW_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_USER_STRING)) {
            moduleViewMap = AccessUtil.ASSET_USER_VIEW_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_TECHNICIAN_STRING)) {
            moduleViewMap = AccessUtil.TECHNICIAN_VIEW_MODULE_MAP;
        }
        this.scanner = scanner == null ? new Scanner(System.in) : scanner;
    }

    @Override
    public void execute() {
        do{
            try {
                System.out.println("\n");
                showModules();
                int option = !AppUtil.isFromTest() ? scanner.nextInt() : Integer.parseInt(scanner.nextLine().trim());
                if(!moduleViewMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                else if(moduleViewMap.get(option).startsWith("Logout")) {
                    AppUtil.handleLoginView();
                }
                else if(moduleViewMap.get(option).startsWith("Exit")) {
                   return;
                }
                String handlerName = AccessModule.getHandler(moduleViewMap.get(option));
                if(AppUtil.isFromTest()) { HandlerFactory.instantiateHandlerWithScanner(handlerName, scanner).execute();} else { HandlerFactory.instantiateHandler(handlerName).execute();}

            }
            catch (Exception e){ System.out.println("");}
        }
        while (CanRunAgain);
    }

    private void showModules() {
        for (Map.Entry<Integer, String> entry : moduleViewMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
}
