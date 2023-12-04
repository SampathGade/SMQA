package entity.purchaseorder;

import java.util.List;

public interface PurchaseOrderInterface {
    PurchaseOrder get(Integer orderID);
    void add(PurchaseOrder purchaseOrder);
    void update(Integer orderID, PurchaseOrder purchaseOrder);
    void delete(Integer orderID);
    List<PurchaseOrder> getAll();
}
