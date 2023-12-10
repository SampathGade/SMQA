package mobileclientassetmanagement.src.entity.category;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import mobileclientassetmanagement.src.util.imports.ImportFactory;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CategoryUtil {
    public static final String[] EXPORT_HEADER = {"CategoryID", "CategoryName", "CategoryDescription"};
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("categoryID");
    public static Integer generateCategoryID() {
        Integer categoryID = 1;
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        Map.Entry<Integer, Category> lastEntry = null;
        for(Map.Entry<Integer, Category> categoryEntry: categoryDataMap.entrySet()){
            lastEntry = categoryEntry;
        }
        categoryID = lastEntry == null ? categoryID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return categoryID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "Mobile Phone\nMobile Phone Description\n1\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForCategoryData() {
        String resourcesFolder = "src/resources";
        String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, resourcesFolder, Constants.CATEGORY_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleCategoryImportForTest() {
        String resourcePath = getPathForCategoryData();
        ImportFactory.getHandler(Constants.CATEGORY).handleImport(resourcePath);
    }
}
