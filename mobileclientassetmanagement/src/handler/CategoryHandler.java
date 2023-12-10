package mobileclientassetmanagement.src.handler;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.category.Category;
import mobileclientassetmanagement.src.entity.category.CategoryFactoryImpl;
import mobileclientassetmanagement.src.entity.category.CategoryInterface;
import mobileclientassetmanagement.src.entity.category.CategoryUtil;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class CategoryHandler implements Handler {

    private Map<Integer, String> categoryHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }

    public CategoryHandler() {
        this(null);
    }
    public CategoryHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            categoryHandlerMap = AccessUtil.ADMIN_CATEGORY_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            categoryHandlerMap = AccessUtil.ASSET_MANAGER_CATEGORY_MODULE_MAP;
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
                if(!categoryHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(categoryHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(categoryHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(categoryHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(categoryHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(categoryHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); e.printStackTrace();}
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : categoryHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Category Creation....");
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        Category category = new Category();
        handleFieldInput(category);
        category.setCategoryID(CategoryUtil.generateCategoryID());
        Integer categoryID = category.getCategoryID();
        CategoryInterface categoryInterface = new CategoryFactoryImpl().createCategory();
        categoryInterface.add(category);
        System.out.println("Category Created Successfully!!!!");
        displayCategoryDetailsVertical(categoryDataMap.get(categoryID));
    }

    private Category handleRetrieve() {
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        displayAllCategories();
        int providedID = scanner.nextInt();
        Category providedCategory = categoryDataMap.get(providedID);
        System.out.println("You have chosen the following Category");
        displayCategoryDetailsVertical(providedCategory);
        return providedCategory;
    }

    private void handleUpdate() {
        Category categoryToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = CategoryUtil.getTestInputForUpdate();
        }
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        handleFieldInput(categoryToBeUpdated);
        Integer categoryID = categoryToBeUpdated.getCategoryID();
        CategoryInterface categoryInterface = new CategoryFactoryImpl().createCategory();
        categoryInterface.update(categoryID, categoryToBeUpdated);
        System.out.println("Category Updated Successfully!!!!");
        displayCategoryDetailsVertical(categoryDataMap.get(categoryID));
    }

    private void handleDelete() {
        Category categoryToBeDeleted = handleRetrieve();
        Integer categoryID = categoryToBeDeleted.getCategoryID();
        CategoryInterface categoryInterface = new CategoryFactoryImpl().createCategory();
        categoryInterface.delete(categoryID);
        System.out.println("Category Deleted Successfully!!!!");
    }

    private void displayAllCategories() {
        System.out.println("Displaying All Categories:");
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        for(Map.Entry<Integer, Category> categoryEntry : categoryDataMap.entrySet()){
            int categoryID = categoryEntry.getKey();
            Category category = categoryEntry.getValue();
            System.out.println(categoryID);
            displayCategoryDetailsHorizontal(category);
        }
    }

    private void handleFieldInput(Category category) {
        try {
            Field[] categoryFields = Category.class.getDeclaredFields();
            for(Field field : categoryFields) {
                if(CategoryUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == String.class){
                    String input = scanner.nextLine();
                    field.set(category, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input");}
    }
    
    private static void displayCategoryDetailsVertical(Category category) {
        System.out.println("\nCategory details:");
        System.out.println("CategoryID: " +category.getCategoryID());
        System.out.println("CategoryName: " +category.getCategoryName());
        System.out.println("CategoryDescription: " +category.getCategoryDescription());
    }

    private static void displayCategoryDetailsHorizontal(Category category) {
        System.out.print("CategoryID: " + category.getCategoryID()+", ");
        System.out.print("CategoryName: " + category.getCategoryName()+", ");
        System.out.print("CategoryDescription: " + category.getCategoryDescription());
    }
}
