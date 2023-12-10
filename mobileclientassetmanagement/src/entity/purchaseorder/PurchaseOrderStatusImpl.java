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

    @Override
    public void push(Object entity) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) entity;
        Integer orderID = purchaseOrder.getOrderID();
        Integer pushStatusCode = PurchaseOrderStatus.PUSH.getStatusCode();
        purchaseOrder.setStatus(pushStatusCode);
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.update(orderID, purchaseOrder);
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        for(PurchaseOrder.Item orderItem : purchaseOrder.getOrderItems()) {
            Asset newAsset = new Asset(AssetUtil.generateAssetID(), orderItem.getItemName());
            assetInterface.add(newAsset);
        }
    }
}
