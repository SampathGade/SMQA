package mobileclientassetmanagement.src.dbmanager;

import mobileclientassetmanagement.src.entity.asset.Asset;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequest;
import mobileclientassetmanagement.src.entity.category.Category;
import mobileclientassetmanagement.src.entity.location.Location;
import mobileclientassetmanagement.src.entity.maintenancerequest.MaintenanceRequest;
import mobileclientassetmanagement.src.entity.product.Product;
import mobileclientassetmanagement.src.entity.project.Project;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrder;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.entity.vendor.Vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManager
{
    private static Map<Integer, User> userDataMap = new HashMap<>();
    private static Map<Integer, AssetRequest> assetRequestDataMap = new HashMap<>();
    private static Map<Integer, Location> locationDataMap = new HashMap<>();
    private static Map<Integer, Project> projectDataMap = new HashMap<>();
    private static Map<Integer, Product> productDataMap = new HashMap<>();
    private static Map<Integer, Category> categoryDataMap = new HashMap<>();
    private static Map<Integer, PurchaseOrder> purchaseOrderDataMap = new HashMap<>();
    private static Map<Integer, Asset> assetDataMap = new HashMap<>();
    private static Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = new HashMap<>();
    private static Map<Integer, Vendor> vendorDataMap = new HashMap<>();

    private DataManager()
    {

    }

    public static Map<Integer, User> getUserData(){
        return userDataMap;
    }
    public static Map<Integer, Category> getCategoryData(){return categoryDataMap;}
    public static Map<Integer, Location> getLocationData() {return locationDataMap;}
    public static Map<Integer, Asset> getAssetData() {return assetDataMap;}

    public static Map<Integer, Project> getProjectData() {return projectDataMap;}
    public static Map<Integer, Product> getProductData() {return productDataMap;}
    public static Map<Integer, AssetRequest> getAssetRequestData() {return assetRequestDataMap;}
    public static Map<Integer, PurchaseOrder> getPurchaseOrderData(){return purchaseOrderDataMap;}
    public static Map<Integer, Vendor> getVendorData() {return vendorDataMap; }
    public static Map<Integer, PurchaseOrder> getPurchaseOrderData(Integer status) {
        return getPurchaseOrderData().entrySet().stream()
                .filter(entry -> {PurchaseOrder purchaseOrder = entry.getValue();
                    return purchaseOrder.getStatus().equals(status);})
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public static Map<Integer, AssetRequest> getAssetRequestData(String requesterName) {
        return assetRequestDataMap.entrySet().stream()
                .filter(entry -> {AssetRequest assetRequest = entry.getValue();
                    return assetRequest.getRequesterName() != null && assetRequest.getRequesterName().getName().equals(requesterName);})
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, AssetRequest> getAssetRequestData(Boolean isSelf, String requesterName, Integer status) {
        return getAssetRequestData(isSelf, requesterName).entrySet().stream()
                .filter(entry -> {AssetRequest assetRequest = entry.getValue();
                    return assetRequest.getRequestStatus().equals(status);})
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, AssetRequest> getAssetRequestData(Boolean isSelf, String requesterName) {
        if(!isSelf) return getAssetRequestData();
        return getAssetRequestData(requesterName);
    }

    public static Map<Integer, MaintenanceRequest> getMaintenanceRequestData() {return maintenanceRequestDataMap;}
    public static Map<Integer, MaintenanceRequest> getMaintenanceRequestData(String requesterName) {
        return maintenanceRequestDataMap.entrySet().stream()
                .filter(entry -> {MaintenanceRequest maintenanceRequest = entry.getValue();
                    return maintenanceRequest.getRequesterName() != null && maintenanceRequest.getRequesterName().getName().equals(requesterName);})
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, MaintenanceRequest> getMaintenanceRequestData(Boolean isSelf, String requesterName, Integer status) {
        return getMaintenanceRequestData(isSelf, requesterName).entrySet().stream()
                .filter(entry -> {MaintenanceRequest maintenanceRequest = entry.getValue();
                    return maintenanceRequest.getRequestStatus().equals(status);})
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, MaintenanceRequest> getMaintenanceRequestData(Boolean isSelf, String requesterName) {
        if(!isSelf) return getMaintenanceRequestData();
        return getMaintenanceRequestData(requesterName);
    }
}
