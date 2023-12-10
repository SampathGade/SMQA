package mobileclientassetmanagement.src.entity.purchaseorder;

import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderInterface {
    void add(PurchaseOrder purchaseOrder);
    void update(Integer orderID, PurchaseOrder purchaseOrder);
    void delete(Integer orderID);
}
