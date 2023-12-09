package mobileclientassetmanagement.src.util;

import java.util.HashMap;
import java.util.Map;

public class AccessUtil {
    public static final Map<Integer, String> ADMIN_VIEW_MODULE_MAP = new HashMap<>() {
        {
            put(1, "User");
            put(2, "Asset");
            put(3, "Asset Request");
            put(4, "Maintenance Request");
            put(5, "Category");
            put(6, "Location");
            put(7, "Purchase Order");
            put(8, "Vendor");
            put(9, "Product");
            put(10, "Project");
            put(11, "About Me");
            put(12, "Logout");
            put(13, "Exit");
        }
    };

    public static final Map<Integer, String> ASSET_MANAGER_VIEW_MODULE_MAP = new HashMap<>() {
        {
            put(1, "User");
            put(2, "Asset");
            put(3, "Asset Request");
            put(4, "Maintenance Request");
            put(5, "Category");
            put(6, "Location");
            put(7, "Project");
            put(8, "About Me");
            put(9, "Logout");
            put(10, "Exit");
        }
    };

    public static final Map<Integer, String> ASSET_USER_VIEW_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Asset");
            put(2, "Asset Request");
            put(3, "Maintenance Request");
            put(4, "About Me");
            put(5, "Logout");
            put(6, "Exit");
        }
    };

    public static final Map<Integer, String> TECHNICIAN_VIEW_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Asset");
            put(2, "Asset Request");
            put(3, "Maintenance Request");
            put(4, "Location");
            put(5, "About Me");
            put(6, "Logout");
            put(7, "Exit");
        }
    };


    /* Admin */
    public static final Map<Integer, String> ADMIN_USER_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New User");
            put(2, "Retrieve User By ID");
            put(3, "Update Existing User");
            put(4, "Delete User");
            put(5, "Import User");
            put(6, "Export User");
            put(7, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_ASSET_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Asset");
            put(2, "Retrieve Asset By ID");
            put(3, "Update Existing Asset");
            put(4, "Delete Asset");
            put(5, "Import Asset");
            put(6, "Export Asset");
            put(7, "Move Asset");
            put(8, "Tag Asset By User");
            put(9, "Tag Asset By Project");
            put(10, "Mark Asset As Expired");
            put(11, "Mark Asset as Decommission");
            put(12, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_ASSET_REQUEST_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Asset Request");
            put(2, "Retrieve Request By ID");
            put(3, "Update Asset Request");
            put(4, "Delete Asset Request");
            put(5, "View Open Asset Request (All Open Asset Request) "); // (All Users)
            put(6, "View All Asset Request"); // (All Users)
            put(7, "Approve Asset Request");
            put(8, "Reject Asset Request");
            put(9, "Comment on Asset Request"); // (All Users)
            put(10, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_MAINTENANCE_REQUEST_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Maintenance Request");
            put(2, "Retrieve Request By ID");
            put(3, "Update Maintenance Request");
            put(4, "Delete Maintenance Request");
            put(5, "View Open Maintenance Request (All Open Asset Request) "); // (All Users)
            put(6, "View All Maintenance Request"); // (All Users)
            put(7, "Close Maintenance Request");
            put(8, "Comment on Maintenance Request"); // (All Users)
            put(9, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_CATEGORY_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Category");
            put(2, "Retrieve Category By ID");
            put(3, "Update Existing Category");
            put(4, "Delete Category");
            put(5, "Import Category");
            put(6, "Export Category");
            put(7, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_LOCATION_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Location");
            put(2, "Retrieve Location By ID");
            put(3, "Update Existing Location");
            put(4, "Delete Location");
            put(5, "Import Location");
            put(6, "Export Location");
            put(7, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_PURCHASE_ORDER_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Purchase Order");
            put(2, "Retrieve PurchaseOrder By ID");
            put(3, "Update Existing Order");
            put(4, "Delete Order");
            put(5, "Mark As Paid");
            put(6, "Push Item To Asset");
            put(7, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_VENDOR_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Vendor");
            put(2, "Retrieve Vendor By ID");
            put(3, "Update Existing Vendor");
            put(4, "Delete Vendor");
            put(5, "Import Vendor");
            put(6, "Export Vendor");
            put(7, "Associate Product");
            put(8, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_PRODUCT_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Product");
            put(2, "Retrieve Product By ID");
            put(3, "Update Existing Product");
            put(4, "Delete Product");
            put(5, "Import Product");
            put(6, "Export Product");
            put(7, "Exit");
        }
    };

    public static final Map<Integer, String> ADMIN_PROJECT_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Project");
            put(2, "Retrieve Project By ID");
            put(3, "Update Existing Project");
            put(4, "Delete Project");
            put(5, "Import Project");
            put(6, "Export Project");
            put(7, "Exit");
        }
    };
    /* Admin */






    /* Asset Manager */
    public static final Map<Integer, String> ASSET_MANAGER_USER_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Retrieve User By ID");
            put(2, "Export User");
            put(3, "Exit");
        }
    };
    public static final Map<Integer, String> ASSET_MANAGER_ASSET_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Asset");
            put(2, "Retrieve Asset By ID");
            put(3, "Update Existing Asset");
            put(4, "Delete Asset");
            put(5, "Import Asset");
            put(6, "Export Asset");
            put(7, "Move Asset");
            put(8, "Tag Asset By User");
            put(9, "Tag Asset By Project");
            put(10, "Mark Asset As Expired");
            put(11, "Exit");
        }
    };;

    public static final Map<Integer, String> ASSET_MANAGER_ASSET_REQUEST_MODULE_MAP = ADMIN_ASSET_REQUEST_MODULE_MAP;
    public static final Map<Integer, String> ASSET_MANAGER_MAINTENANCE_REQUEST_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Maintenance Request");
            put(2, "Retrieve Request By ID");
            put(3, "Update Maintenance Request");
            put(4, "Delete MaintenanceRequest");
            put(5, "View Open Maintenance Request (My Open Maintenance Request)"); // Self (Current User)
            put(6, "Comment on Maintenance Request"); // Self (Current User)
            put(7, "Exit");
        }
    };
    public static final Map<Integer, String> ASSET_MANAGER_CATEGORY_MODULE_MAP = ADMIN_CATEGORY_MODULE_MAP;
    public static final Map<Integer, String> ASSET_MANAGER_LOCATION_MODULE_MAP = ADMIN_LOCATION_MODULE_MAP;
    public static final Map<Integer, String> ASSET_MANAGER_PROJECT_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Retrieve Project By ID");
            put(2, "Export Project");
            put(3, "Exit");
        }
    };
    /* Asset Manager */






    /* Asset User */
    public static final Map<Integer, String> ASSET_USER_ASSET_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Retrieve Asset By ID");
            put(2, "Exit");
        }
    };
    public static final Map<Integer, String> ASSET_USER_ASSET_REQUEST_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Create New Asset Request");
            put(2, "Retrieve Request By ID");
            put(3, "Update Asset Request");
            put(4, "Delete Asset Request");
            put(5, "View Open Asset Request (My Open Asset Request)"); // Self (Current User)
            put(6, "Comment on Asset Request"); // Self (Current User)
            put(7, "Exit");
        }
    };

    public static final Map<Integer, String> ASSET_USER_MAINTENANCE_REQUEST_MODULE_MAP = ASSET_MANAGER_MAINTENANCE_REQUEST_MODULE_MAP;
    /* Asset User */






    /* Technician */
    public static final Map<Integer, String> TECHNICIAN_ASSET_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Retrieve Asset By ID");
            put(2, "Export Asset");
            put(3, "Exit");
        }
    };

    public static final Map<Integer, String> TECHNICIAN_ASSET_REQUEST_MODULE_MAP = ASSET_USER_ASSET_REQUEST_MODULE_MAP;
    public static final Map<Integer, String> TECHNICIAN_MAINTENANCE_REQUEST_MODULE_MAP = ADMIN_MAINTENANCE_REQUEST_MODULE_MAP;
    public static final Map<Integer, String> TECHNICIAN_LOCATION_MODULE_MAP = new HashMap<>() {
        {
            put(1, "Retrieve Location By ID");
            put(2, "Export Location");
            put(3, "Exit");
        }
    };
    /* Technician */
}
