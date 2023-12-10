package entity.purchaseorder;


import entity.asset.Asset;
import entity.asset.AssetFactoryImpl;
import entity.asset.AssetInterface;
import entity.asset.AssetUtil;
import status.Status;

public class PurchaseOrderStatusImpl extends Status {
    @Override
    public void markAsPaid(Object entity) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) entity;
        Integer orderID = purchaseOrder.getOrderID();
        Integer paidStatusCode = PurchaseOrderStatus.PAID.getStatusCode();
        purchaseOrder.setStatus(paidStatusCode);
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.update(orderID, purchaseOrder);
    }
}
