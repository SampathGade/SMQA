package mobileclientassetmanagement.src.entity.purchaseorder;


public interface PurchaseOrderInterface {
    void add(PurchaseOrder purchaseOrder);
    void update(Integer orderID, PurchaseOrder purchaseOrder);
    void delete(Integer orderID);
}
