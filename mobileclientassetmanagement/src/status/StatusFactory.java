package mobileclientassetmanagement.src.status;
import mobileclientassetmanagement.src.entity.asset.AssetStatusImpl;
import mobileclientassetmanagement.src.entity.maintenancerequest.MaintenanceRequestStatusImpl;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrderStatusImpl;

public class StatusFactory {
    public static StatusInterface getObject(int objectType) {
        switch (objectType) {
            case 2:
                return new AssetStatusImpl();
            case 4:
                return new MaintenanceRequestStatusImpl();
            case 5:
                return new PurchaseOrderStatusImpl();
            default:
                throw new IllegalArgumentException("Invalid type..");
        }
    }
}
