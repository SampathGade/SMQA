package handler;

import dbmanager.DataManager;
import entity.product.Product;
import entity.project.ProjectUtil;
import entity.purchaseorder.*;
import entity.useraccount.User;
import entity.vendor.Vendor;
import status.StatusFactory;
import util.AccessUtil;
import util.AppUtil;
import util.Constants;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class PurchaseOrderHandler implements Handler {

    private Map<Integer, String> purchaseOrderHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }

    public PurchaseOrderHandler() {
        this(null);
    }
    public PurchaseOrderHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            purchaseOrderHandlerMap = AccessUtil.ADMIN_PURCHASE_ORDER_MODULE_MAP;
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
                if(!purchaseOrderHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(purchaseOrderHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(purchaseOrderHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(purchaseOrderHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(purchaseOrderHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(purchaseOrderHandlerMap.get(option).startsWith("Mark As Paid")) {
                    markAsPaid();
                }
                else if(purchaseOrderHandlerMap.get(option).startsWith("Push Item To Asset")) {
                    pushItem();
                }
                else if(purchaseOrderHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); }
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : purchaseOrderHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Purchase Order Creation....");
        Map<Integer, PurchaseOrder> purchaseOrderDataMap = DataManager.getPurchaseOrderData();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        handleFieldInput(purchaseOrder);
        purchaseOrder.setOrderID(PurchaseOrderUtil.generateOrderID());
        BigDecimal totalCost = calculateItemTotal(purchaseOrder.getOrderItems());
        purchaseOrder.setTotalCost(totalCost);
        purchaseOrder.setStatus(PurchaseOrderStatus.OPEN.getStatusCode());
        Integer orderID = purchaseOrder.getOrderID();
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.add(purchaseOrder);
        System.out.println("PurchaseOrder Created Successfully!!!!");
        displayPurchaseOrderDetailsVertical(purchaseOrderDataMap.get(orderID));
    }

    private PurchaseOrder handleRetrieve() {
        Map<Integer, PurchaseOrder> purchaseOrderDataMap = DataManager.getPurchaseOrderData();
        displayAllOrders();
        int providedID = scanner.nextInt();
        PurchaseOrder providedOrder = purchaseOrderDataMap.get(providedID);
        System.out.println("You have chosen the following Order");
        displayPurchaseOrderDetailsVertical(providedOrder);
        return providedOrder;
    }

    private void handleUpdate() {
        PurchaseOrder orderToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = PurchaseOrderUtil.getTestInputForUpdate();
        }
        Map<Integer, PurchaseOrder> purchaseOrderDataMap = DataManager.getPurchaseOrderData();
        handleFieldInput(orderToBeUpdated);
        Integer orderID = orderToBeUpdated.getOrderID();
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.update(orderID, orderToBeUpdated);
        System.out.println("PurchaseOrder Updated Successfully!!!!");
        displayPurchaseOrderDetailsVertical(purchaseOrderDataMap.get(orderID));
    }

    private void handleDelete() {
        PurchaseOrder orderToBeDeleted = handleRetrieve();
        Integer orderID = orderToBeDeleted.getOrderID();
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.delete(orderID);
        System.out.println("Product Deleted Successfully!!!!");
    }

    private void displayAllOrders() {
        System.out.println("Displaying All Orders:");
        Map<Integer, PurchaseOrder> orderDataMap = DataManager.getPurchaseOrderData();
        for(Map.Entry<Integer, PurchaseOrder> purchaseOrderEntry : orderDataMap.entrySet()){
            int orderID = purchaseOrderEntry.getKey();
            PurchaseOrder purchaseOrder = purchaseOrderEntry.getValue();
            System.out.println(orderID);
            displayPurchaseOrderDetailsHorizotal(purchaseOrder);
        }
    }

    private void handleFieldInput(PurchaseOrder purchaseOrder) {
        try {
            Field[] purchaseOrderFields = PurchaseOrder.class.getDeclaredFields();
            for(Field field : purchaseOrderFields) {
                if(PurchaseOrderUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == Date.class) {
                    System.out.println("Enter date format in (yyyy-MM-dd):");
                    String dateString = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
                    Date inputDate = dateFormat.parse(dateString);
                    field.set(purchaseOrder, inputDate);
                }
                else if(field.getType() == Vendor.class) {
                    Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
                    System.out.println("Displaying All Vendors");
                    for(Vendor vendor : vendorDataMap.values()) {
                        System.out.println(vendor.getVendorID() + "." + vendor.getVendorName());
                    }
                    System.out.println("Choose Vendor");
                    Integer vendorID = scanner.nextInt();
                    Vendor selectedVendor = vendorDataMap.get(vendorID);
                    field.set(purchaseOrder, selectedVendor);


                    // Showing products based on vendor
                    List<Product> listOfProducts = DataManager.getVendorData().get(vendorID).getAssociatedVendorProduct();
                    System.out.println("Select Products");
                    System.out.println("Enter -1 to complete adding products");
                    Integer productID;
                    List<PurchaseOrder.Item> orderItemList = new ArrayList<>();
                    do {
                        for(Product product:listOfProducts) {
                            System.out.println(product.getProductID() + "." + product.getProductName());
                        }
                        productID = scanner.nextInt();
                        if(productID != -1) {
                            Map<Integer, Product> productDataMap = DataManager.getProductData();
                            Product selectedProduct = productDataMap.get(productID);
                            PurchaseOrder.Item orderItem = new PurchaseOrder.Item();
                            orderItem.setItemName(selectedProduct.getProductName());
                            orderItem.setPrice(selectedProduct.getProductCost());
                            System.out.println("Enter Quantity for the Item");
                            Integer quantity = scanner.nextInt();
                            orderItem.setQuantity(quantity);
                            orderItemList.add(orderItem);
                        }
                    }
                    while (productID != -1);
                    purchaseOrder.setOrderItems(orderItemList);
                }
                else{
                    String input = scanner.nextLine();
                    field.set(purchaseOrder, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input");}
    }

    

    private void displayRequest(Map<Integer, PurchaseOrder> purchaseOrderDataMap) {
        for(Map.Entry<Integer, PurchaseOrder> purchaseOrderEntry : purchaseOrderDataMap.entrySet()) {
            int reqID = purchaseOrderEntry.getKey();
            PurchaseOrder purchaseOrder = purchaseOrderEntry.getValue();
            System.out.print(reqID);
            System.out.print(":\t");
            displayPurchaseOrderDetailsHorizotal(purchaseOrder);
        }
    }

    private void markAsPaid() {
        Map<Integer, PurchaseOrder> purchaseOrderDataMap =  DataManager.getPurchaseOrderData(PurchaseOrderStatus.OPEN.getStatusCode());
        displayRequest(purchaseOrderDataMap);
        int providedID = scanner.nextInt();
        PurchaseOrder providedOrder = purchaseOrderDataMap.get(providedID);
        StatusFactory.getObject(Constants.PURCHASE_REQUEST).markAsPaid(providedOrder);
        System.out.println("Order Paid Successfully!!!");
    }

    private void pushItem() {
        Map<Integer, PurchaseOrder> purchaseOrderDataMap =  DataManager.getPurchaseOrderData(PurchaseOrderStatus.PAID.getStatusCode());
        displayRequest(purchaseOrderDataMap);
        int providedID = scanner.nextInt();
        PurchaseOrder providedOrder = purchaseOrderDataMap.get(providedID);
        StatusFactory.getObject(Constants.PURCHASE_REQUEST).push(providedOrder);
        System.out.println("Item Pushed Successfully!!!");
    }
    
    private static void displayPurchaseOrderDetailsVertical(PurchaseOrder purchaseOrder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.println("\nPurchaseOrder details:");
        System.out.println("PurchaseOrderID: " + purchaseOrder.getOrderID());
        System.out.println("PurchaseOrderDate: " + simpleDateFormat.format(purchaseOrder.getOrderDate()));
        System.out.println("BillingAddress: " + purchaseOrder.getBillingAddress());
        System.out.println("VendorName: " + purchaseOrder.getOrderVendor().getVendorName());
        System.out.println("OrderItems: ");
        List<PurchaseOrder.Item> orderItemList = purchaseOrder.getOrderItems();
        System.out.print("[");
        for(PurchaseOrder.Item orderItem : orderItemList) {
            System.out.println("{");
            System.out.println("ItemName: " + orderItem.getItemName());
            System.out.println("ItemQuantity: " + orderItem.getQuantity());
            System.out.println("ItemPrice: " + orderItem.getPrice());
            System.out.print("}");
        }
        System.out.println("]");
        System.out.println("OrderStatus: " + PurchaseOrderStatus.getStatusName(purchaseOrder.getStatus()));
        System.out.println("Total: " + purchaseOrder.getTotalCost());
    }

    private static void displayPurchaseOrderDetailsHorizotal(PurchaseOrder purchaseOrder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.print("PurchaseOrderID: " + purchaseOrder.getOrderID()+", ");
        System.out.print("PurchaseOrderDate: " + simpleDateFormat.format(purchaseOrder.getOrderDate())+", ");
        System.out.print("BillingAddress: " + purchaseOrder.getBillingAddress()+", ");
        System.out.print("VendorName: " + purchaseOrder.getOrderVendor().getVendorName()+", ");
        System.out.print("OrderItems: ");
        List<PurchaseOrder.Item> orderItemList = purchaseOrder.getOrderItems();
        System.out.print("[");
        for(PurchaseOrder.Item orderItem : orderItemList) {
            System.out.print("{");
            System.out.print("ItemName: " + orderItem.getItemName()+", ");
            System.out.print("ItemQuantity: " + orderItem.getQuantity()+", ");
            System.out.print("ItemPrice: " + orderItem.getPrice());
            System.out.print("}");
        }
        System.out.print("]"+", ");
        System.out.print("OrderStatus: " + PurchaseOrderStatus.getStatusName(purchaseOrder.getStatus()));
        System.out.print("Total: " + purchaseOrder.getTotalCost());
    }

    private static BigDecimal calculateItemTotal(List<PurchaseOrder.Item> itemList) {
        BigDecimal orderTotal = BigDecimal.ZERO;
        for(PurchaseOrder.Item orderItem : itemList) {
            BigDecimal itemTotal = orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            orderTotal = orderTotal.add(itemTotal);
        }
        return orderTotal;
    }
}
