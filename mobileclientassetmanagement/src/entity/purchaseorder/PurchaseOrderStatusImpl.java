package mobileclientassetmanagement.src.entity.purchaseorder;

import mobileclientassetmanagement.src.entity.asset.Asset;
import mobileclientassetmanagement.src.entity.asset.AssetFactoryImpl;
import mobileclientassetmanagement.src.entity.asset.AssetInterface;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
import mobileclientassetmanagement.src.status.Status;

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
