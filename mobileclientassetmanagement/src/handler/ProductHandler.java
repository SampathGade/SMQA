package mobileclientassetmanagement.src.handler;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.product.Product;
import mobileclientassetmanagement.src.entity.product.ProductFactoryImpl;
import mobileclientassetmanagement.src.entity.product.ProductInterface;
import mobileclientassetmanagement.src.entity.product.ProductUtil;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import mobileclientassetmanagement.src.util.exports.ExportFactory;
import mobileclientassetmanagement.src.util.imports.ImportFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class ProductHandler implements Handler{

    private Map<Integer, String> productHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }

    public ProductHandler() {
        this(null);
    }
    public ProductHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            productHandlerMap = AccessUtil.ADMIN_PRODUCT_MODULE_MAP;
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
                if(!productHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(productHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(productHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(productHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(productHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(productHandlerMap.get(option).startsWith("Import")) {
                    handleImport(scanner);
                }
                else if(productHandlerMap.get(option).startsWith("Export")) {
                    handleExport();
                }
                else if(productHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); }
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : productHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Product Creation....");
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        Product product = new Product();
        handleFieldInput(product);
        product.setProductID(ProductUtil.generateProductID());
        Integer productID = product.getProductID();
        ProductInterface productInterface = new ProductFactoryImpl().createProduct();
        productInterface.add(product);
        System.out.println("Product Created Successfully!!!!");
        displayProductDetailsVertical(productDataMap.get(productID));
    }

    private Product handleRetrieve() {
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        displayAllProducts();
        int providedID = scanner.nextInt();
        Product providedProduct = productDataMap.get(providedID);
        System.out.println("You have chosen the following Location");
        displayProductDetailsVertical(providedProduct);
        return providedProduct;
    }

    private void handleUpdate() {
        Product productToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = ProductUtil.getTestInputForUpdate();
        }
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        handleFieldInput(productToBeUpdated);
        Integer productID = productToBeUpdated.getProductID();
        ProductInterface productInterface = new ProductFactoryImpl().createProduct();
        productInterface.update(productID, productToBeUpdated);
        System.out.println("Product Updated Successfully!!!!");
        displayProductDetailsVertical(productDataMap.get(productID));
    }

    private void handleDelete() {
        Product productToBeDeleted = handleRetrieve();
        Integer productID = productToBeDeleted.getProductID();
        ProductInterface productInterface = new ProductFactoryImpl().createProduct();
        productInterface.delete(productID);
        System.out.println("Product Deleted Successfully!!!!");
    }

    private void displayAllProducts() {
        System.out.println("Displaying All Products:");
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        for(Map.Entry<Integer, Product> productEntry : productDataMap.entrySet()){
            int productID = productEntry.getKey();
            Product product = productEntry.getValue();
            System.out.println(productID);
            displayProductDetailsHorizotal(product);
        }
    }

    private void handleFieldInput(Product product) {
        try {
            Field[] productFields = Product.class.getDeclaredFields();
            for(Field field : productFields) {
                if(ProductUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if (field.getType() == BigDecimal.class) {
                    BigDecimal decimalInput = scanner.nextBigDecimal();
                    field.set(product, decimalInput);
                }
                else{
                    String input = scanner.nextLine();
                    field.set(product, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input"); }
    }

    private void handleImport(Scanner scanner) {
        System.out.println("Please provide Absolute Path to Import");
        scanner.nextLine();
        String filePath = scanner.nextLine();
        ImportFactory.getHandler(Constants.PRODUCT).handleImport(filePath);
        System.out.println("Import Successfully Completed");
    }

    private void handleExport() {
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        ExportFactory.getHandler(Constants.PRODUCT).handleExport(productDataMap, "");
        System.out.println("Export Completed");
    }

    private static void displayProductDetailsVertical(Product product) {
        System.out.println("\nProduct details:");
        System.out.println("ProductID: " + product.getProductID());
        System.out.println("ProductName: " + product.getProductName());
        System.out.println("ProductDescription: " + product.getProductDescription());
        System.out.println("ProductCost: " + product.getProductCost());
    }

    private static void displayProductDetailsHorizotal(Product product) {
        System.out.print("ProductID: " + product.getProductID()+", ");
        System.out.print("ProductName: " + product.getProductName()+", ");
        System.out.print("ProductDescription: " + product.getProductDescription()+", ");
        System.out.print("ProductCost: " + product.getProductCost());
    }
}
