package mobileclientassetmanagement.src.entity.purchaseorder;
import mobileclientassetmanagement.src.dbmanager.DataManager;

import java.util.Map;

public class PurchaseOrderImpl implements PurchaseOrderInterface{
    private Map<Integer, PurchaseOrder> purchaseOrderData;
    PurchaseOrderImpl() {
        this.purchaseOrderData = DataManager.getPurchaseOrderData();
    }
    @Override
    public void add(PurchaseOrder purchaseOrder) {
        purchaseOrderData.put(purchaseOrder.getOrderID(), purchaseOrder);
    }

    @Override
    public void update(Integer orderID, PurchaseOrder purchaseOrder) {
        purchaseOrderData.put(orderID, purchaseOrder);
    }

    @Override
    public void delete(Integer orderID) {
        purchaseOrderData.remove(orderID);
    }
}
