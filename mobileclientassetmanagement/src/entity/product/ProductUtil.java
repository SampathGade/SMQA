package entity.product;

import dbmanager.DataManager;
import util.AppUtil;
import util.Constants;
import util.imports.ImportFactory;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductUtil {
    public static final String[] EXPORT_HEADER = {"ProductID", "ProductName", "ProductDescription", "ProductCost"};
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("productID");
    public static Integer generateProductID() {
        Integer productID = 1;
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        Map.Entry<Integer, Product> lastEntry = null;
        for(Map.Entry<Integer, Product> productEntry : productDataMap.entrySet()){
            lastEntry = productEntry;
        }
        productID = lastEntry == null ? productID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return productID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "Printer Test \nHigh-speed printer for office use Test\n350\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForProductData() {
        String resourcesFolder = "src/resources";
        String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, resourcesFolder, Constants.PRODUCT_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleProductImport() {
        String resourcePath = getPathForProductData();
        ImportFactory.getHandler(Constants.PRODUCT).handleImport(resourcePath);
    }
}
