package entity.purchaseorder;

import dbmanager.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PurchaseOrderImpl implements PurchaseOrderInterface{
    private Map<Integer, PurchaseOrder> purchaseOrderData;
    PurchaseOrderImpl() {
        this.purchaseOrderData = DataManager.getPurchaseOrderData();
    }
    @Override
    public PurchaseOrder get(Integer orderID) {
        return purchaseOrderData.get(orderID);
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

    @Override
    public List<PurchaseOrder> getAll() {
        return new ArrayList<>(purchaseOrderData.values());
    }
}
