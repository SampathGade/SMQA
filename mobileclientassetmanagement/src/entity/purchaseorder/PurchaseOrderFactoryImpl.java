package entity.purchaseorder;

public class PurchaseOrderFactoryImpl implements PurchaseOrderFactory{
    @Override
    public PurchaseOrderInterface createPurchaseOrder() {
        return new PurchaseOrderImpl();
    }
}
