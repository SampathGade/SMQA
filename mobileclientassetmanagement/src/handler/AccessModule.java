package mobileclientassetmanagement.src.handler;


public enum AccessModule {
    USER("User", "UserHandler"),
    ASSET("Asset", "AssetHandler"),
    ASSET_REQUEST("Asset Request", "AssetRequestHandler"),
    MAINTENANCE_REQUEST("Maintenance Request", "MaintenanceRequestHandler"),
    CATEGORY("Category", "CategoryHandler"),
    LOCATION("Location", "LocationHandler"),
    PURCHASE_ORDER("Purchase Order", "PurchaseOrderHandler"),
    VENDOR("Vendor", "VendorHandler"),
    PRODUCT("Product", "ProductHandler"),
    PROJECT("Project", "ProjectHandler"),
    ABOUT_ME("About Me", "ProfileHandler");

    private String moduleName;
    private String handlerName;

    AccessModule(String moduleName, String handlerName) {
        this.moduleName = moduleName;
        this.handlerName = handlerName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public static String getHandler(String moduleName){
        for(AccessModule accessModule : AccessModule.values()){
            if(accessModule.getModuleName().equals(moduleName)) {
                return accessModule.getHandlerName();
            }
        }
        throw new IllegalArgumentException("No Handler found for the given module");
    }
}
