package mobileclientassetmanagement.src.status;
import mobileclientassetmanagement.src.entity.asset.AssetStatusImpl;

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
