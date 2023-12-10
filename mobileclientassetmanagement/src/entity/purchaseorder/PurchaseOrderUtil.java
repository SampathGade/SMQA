package mobileclientassetmanagement.src.entity.purchaseorder;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrder;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PurchaseOrderUtil {
    public static final Integer OPEN = 0;
    public static final Integer PAID = 4;
    public static final Integer PUSH = 5;

    public static final String OPEN_STRING = "Open";
    public static final String PAID_STRING = "Paid";
    public static final String PUSH_STRING = "Push";
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("orderID", "itemID", "totalCost", "status", "orderItems");
    public static Integer generateOrderID() {
        Integer orderID = 1;
        Map<Integer, PurchaseOrder> purchaseOrderDataMap = DataManager.getPurchaseOrderData();
        Map.Entry<Integer, PurchaseOrder> lastEntry = null;
        for(Map.Entry<Integer, PurchaseOrder> purchaseOrderEntry : purchaseOrderDataMap.entrySet()){
            lastEntry = purchaseOrderEntry;
        }
        orderID = lastEntry == null ? orderID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return orderID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "2023-12-15\nVictoria Avenue Test\n1\n1\n1\n-1";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
