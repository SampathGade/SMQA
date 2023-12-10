package mobileclientassetmanagement.src.entity.purchaseorder;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PurchaseOrderUtil {
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("orderID", "itemID", "totalCost");
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
}
