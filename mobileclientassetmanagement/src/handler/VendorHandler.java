package mobileclientassetmanagement.src.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.product.Product;
import mobileclientassetmanagement.src.entity.vendor.Vendor;
import mobileclientassetmanagement.src.entity.vendor.VendorFactoryImpl;
import mobileclientassetmanagement.src.entity.vendor.VendorInterface;
import mobileclientassetmanagement.src.entity.vendor.VendorUtil;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import mobileclientassetmanagement.src.util.exports.ExportFactory;
import mobileclientassetmanagement.src.util.imports.ImportFactory;

public class VendorHandler implements Handler {
	private Map<Integer, String> vendorHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }
    public VendorHandler() {
    	this(null);
    }
    public VendorHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            vendorHandlerMap = AccessUtil.ADMIN_VENDOR_MODULE_MAP;
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
                if(!vendorHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(vendorHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(vendorHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(vendorHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(vendorHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(vendorHandlerMap.get(option).startsWith("Associate Product")) {
                    associateProductWithVendor();
                }
                else if(vendorHandlerMap.get(option).startsWith("Import")) {
                    handleImport(scanner);
                }
                else if(vendorHandlerMap.get(option).startsWith("Export")) {
                    handleExport();
                }
                else if(vendorHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); }
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : vendorHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Vendor Creation....");
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        Vendor vendor = new Vendor();
        handleFieldInput(vendor);
        vendor.setVendorID(VendorUtil.generateVendorID());
        Integer vendorID = vendor.getVendorID();
        VendorInterface vendorInterface = new VendorFactoryImpl().createVendor();
        vendorInterface.add(vendor);
        System.out.println("Vendor Created Successfully!!!!");
        displayVendorDetailsVertical(vendorDataMap.get(vendorID));
    }

    private Vendor handleRetrieve() {
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        displayAllVendors();
        int providedID = scanner.nextInt();
        Vendor providedVendor = vendorDataMap.get(providedID);
        System.out.println("You have chosen the following Category");
        displayVendorDetailsVertical(providedVendor);
        return providedVendor;
    }

    private void handleUpdate() {
        Vendor vendorToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = VendorUtil.getTestInputForUpdate();
        }
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        handleFieldInput(vendorToBeUpdated);
        Integer vendorID = vendorToBeUpdated.getVendorID();
        VendorInterface vendorInterface = new VendorFactoryImpl().createVendor();
        vendorInterface.update(vendorID, vendorToBeUpdated);
        System.out.println("Vendor Updated Successfully!!!!");
        displayVendorDetailsVertical(vendorDataMap.get(vendorID));
    }

    private void handleDelete() {
        Vendor vendorToBeDeleted = handleRetrieve();
        Integer vendorID = vendorToBeDeleted.getVendorID();
        VendorInterface vendorInterface = new VendorFactoryImpl().createVendor();
        vendorInterface.delete(vendorID);
        System.out.println("Vendor Deleted Successfully!!!!");
    }

    private void displayAllVendors() {
        System.out.println("Displaying All Vendors:");
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        for(Map.Entry<Integer, Vendor> vendorEntry : vendorDataMap.entrySet()){
            int vendorID = vendorEntry.getKey();
            Vendor vendor = vendorEntry.getValue();
            System.out.println(vendorID);
            displayVendorDetailsHorizontal(vendor);
        }
    }

    private void handleFieldInput(Vendor vendor) {
        try {
            Field[] vendorFields = Vendor.class.getDeclaredFields();
            for(Field field : vendorFields) {
                if(VendorUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == String.class){
                    String input = scanner.nextLine();
                    field.set(vendor, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input");}
    }

    private void associateProductWithVendor() {
        System.out.println("Select the vendor to Associate with product");
        Vendor selectedVendor = handleRetrieve();
        List<Product> productList = new ArrayList<>();
        Integer productID;
        do {
            System.out.println("Displaying All Products:");
            System.out.println("Press -1 to End");
            for(Product product : DataManager.getProductData().values()) {
                System.out.println(product.getProductID() + "." + product.getProductName());
            }
            productID = scanner.nextInt();
            if(productID != -1) {
                productList.add(DataManager.getProductData().get(productID));
            }
        }
        while (productID != -1);
        selectedVendor.setAssociatedVendorProduct(productList);
    }

    private void handleImport(Scanner scanner) {
        System.out.println("Please provide Absolute Path to Import");
        scanner.nextLine();
        String filePath = scanner.nextLine();
        ImportFactory.getHandler(Constants.VENDOR).handleImport(filePath);
        System.out.println("Import Successfully Completed");
    }

    private void handleExport() {
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        ExportFactory.getHandler(Constants.VENDOR).handleExport(vendorDataMap, "");
        System.out.println("Export Completed");
    }

    private static void displayVendorDetailsVertical(Vendor vendor) {
        System.out.println("\nVendor details:");
        System.out.println("VendorID: " +vendor.getVendorID());
        System.out.println("VendorName: " +vendor.getVendorName());
        System.out.println("VendorEmail: " +vendor.getVendorEmail());
        System.out.println("VendorAddress: " +vendor.getVendorAddress());
        if(vendor.getAssociatedVendorProduct() != null) {
            System.out.println("Associated Product: ");
            System.out.println("[");
            for(Product product : vendor.getAssociatedVendorProduct()) {
                System.out.println("{");
                System.out.println(product.getProductID() +"."+product.getProductName());
                System.out.println("}");
            }
            System.out.println("]");
        }
    }

    private static void displayVendorDetailsHorizontal(Vendor vendor) {
        System.out.print("VendorID: " + vendor.getVendorID()+", ");
        System.out.print("VendorName: " + vendor.getVendorName()+", ");
        System.out.print("VendorEmail: " + vendor.getVendorEmail()+", ");
        if(vendor.getAssociatedVendorProduct() != null) {
            System.out.print("Associated Product: ");
            System.out.print("[");
            for(Product product : vendor.getAssociatedVendorProduct()) {
                System.out.print("{");
                System.out.print(product.getProductID() +"."+product.getProductName());
                System.out.print("}");
            }
            System.out.print("]"+", ");
        }
        System.out.print("VendorAddress: " + vendor.getVendorAddress());
    }
}
