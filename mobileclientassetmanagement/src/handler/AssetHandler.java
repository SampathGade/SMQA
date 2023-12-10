package mobileclientassetmanagement.src.handler;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.*;
import mobileclientassetmanagement.src.entity.category.Category;
import mobileclientassetmanagement.src.entity.location.Location;
import mobileclientassetmanagement.src.entity.project.Project;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class AssetHandler implements Handler {
    private Map<Integer, String> assetHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;

    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }
    public AssetHandler() {
        this(null);
    }
    public AssetHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            assetHandlerMap = AccessUtil.ADMIN_ASSET_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            assetHandlerMap = AccessUtil.ASSET_MANAGER_ASSET_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_USER_STRING)) {
            assetHandlerMap = AccessUtil.ASSET_USER_ASSET_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_TECHNICIAN_STRING)) {
            assetHandlerMap = AccessUtil.TECHNICIAN_ASSET_MODULE_MAP;
        }
        this.scanner = scanner == null ? new Scanner(System.in) : scanner;
    }
    @Override
    public void execute() {
        do {
            try {
                System.out.println("\n");
                showOperations();
                int option = !AppUtil.isFromTest() ? scanner.nextInt() : Integer.parseInt(scanner.nextLine().trim());
                if(!assetHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(assetHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(assetHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(assetHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(assetHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(assetHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); e.printStackTrace();}
        }
        while (CanRunAgain);
    }

    private void handleCreate() {
        System.out.println("New Asset Creation....");
        Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
        Asset asset = new Asset();
        handleFieldInput(asset);
        asset.setAssetID(AssetUtil.generateAssetID());
        asset.setAssetAcqisationDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        asset.setAssetStatus(AssetStatus.UNASSIGNED.getStatusCode());
        Integer assetID = asset.getAssetID();
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.add(asset);
        System.out.println("Asset Created Successfully!!!!");
        displayAssetDetailsVertical(assetDataMap.get(assetID));
    }

    private Asset handleRetrieve() {
        displayAllAssets();
        Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
        int providedID = scanner.nextInt();
        Asset providedAsset = assetDataMap.get(providedID);
        System.out.println("You have chosen the following Asset");
        displayAssetDetailsVertical(providedAsset);
        return providedAsset;
    }

    private void handleUpdate() {
        Asset assetToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = AssetUtil.getTestInputForUpdate();
        }
        Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
        handleFieldInput(assetToBeUpdated);
        Integer assetID = assetToBeUpdated.getAssetID();
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.update(assetID, assetToBeUpdated);
        System.out.println("Asset Updated Successfully!!!!");
        displayAssetDetailsVertical(assetDataMap.get(assetID));
    }

    private void handleDelete() {
        Asset assetToBeDeleted = handleRetrieve();
        Integer assetID = assetToBeDeleted.getAssetID();
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.delete(assetID);
        System.out.println("Asset Deleted Successfully!!!!");
    }

    private void displayAllAssets() {
        System.out.println("Displaying All Assets:");
        Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
        for(Map.Entry<Integer, Asset> assetEntry : assetDataMap.entrySet()){
            int assetID = assetEntry.getKey();
            Asset asset = assetEntry.getValue();
            System.out.print(assetID);
            System.out.print(":\t");
            displayAssetDetailsHorizontal(asset);
            System.out.println("");
        }
    }

    private void handleFieldInput(Asset asset) {
        try {
            Field[] assetFields = Asset.class.getDeclaredFields();
            for(Field field : assetFields) {
                if(AssetUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == Category.class) {
                    Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
                    System.out.println("Displaying All Category");
                    for(Category category:categoryDataMap.values()) {
                        System.out.println(category.getCategoryID() + "." + category.getCategoryName());
                    }
                    System.out.println("Choose Category");
                    Integer categoryID = scanner.nextInt();
                    Category selectedCategory = categoryDataMap.get(categoryID);
                    field.set(asset, selectedCategory);
                    scanner.nextLine();
                }
                else if(field.getType() == Location.class) {
                    Map<Integer, Location> locationDataMap = DataManager.getLocationData();
                    System.out.println("Displaying All Locations");
                    for(Location location : locationDataMap.values()) {
                        System.out.println(location.getLocationID() + "." + location.getLocationName());
                    }
                    System.out.println("Choose Location");
                    Integer locationID = scanner.nextInt();
                    Location selectedLocation = locationDataMap.get(locationID);
                    field.set(asset, selectedLocation);
                }
                else if(field.getType() == Date.class) {
                    System.out.println("Enter date format in (yyyy-MM-dd):");
                    String dateString = AppUtil.isFromTest() ? scanner.next() : scanner.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
                    Date inputDate = dateFormat.parse(dateString);
                    field.set(asset, inputDate);
                }
                else{
                    String input = AppUtil.isFromTest() ? scanner.nextLine() : scanner.next();
                    field.set(asset, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input"); }
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : assetHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private static void displayAssetDetailsVertical(Asset asset) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.println("\nAsset details:");
        System.out.println("AssetID: " + asset.getAssetID());
        System.out.println("AssetName: " +asset.getAssetName());
        if(asset.getAssetCategory() != null)
            System.out.println("AssetCategory: " +asset.getAssetCategory().getCategoryName());
        if(asset.getAssetModel() != null)
            System.out.println("AssetModel: " + asset.getAssetModel());
        if(asset.getAssetDescription() != null)
            System.out.println("AssetDescription: " + asset.getAssetDescription());
        if(asset.getAssetLocation()!= null)
            System.out.println("AssetLocation: " + asset.getAssetLocation().getLocationName());
        System.out.println("AssetStatus: " + asset.getAssetStatus());
        System.out.println("AssetStatus Formatted: " + AssetStatus.getStatusName(asset.getAssetStatus()));
        if(asset.getAssetOwner() instanceof User)
        System.out.println("AssetOwner (User) :" + (String) ((User) asset.getAssetOwner()).getName());
        else if(asset.getAssetOwner() instanceof Project)
            System.out.println("AssetOwner (Project) :" + (String) ((Project) asset.getAssetOwner()).getProjectName());
        if(asset.getAssetAcqisationDate() != null)
            System.out.println("AssetAcquisitionDate: " + simpleDateFormat.format(asset.getAssetAcqisationDate()));
        if(asset.getExpiryDate() != null)
            System.out.println("AssetExpiryDate: " + simpleDateFormat.format(asset.getExpiryDate()));
    }

    private static void displayAssetDetailsHorizontal(Asset asset) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.print("AssetID: " + asset.getAssetID()+", ");
        System.out.print("AssetName: " +asset.getAssetName()+", ");
        if(asset.getAssetCategory() != null)
            System.out.print("AssetCategory: " +asset.getAssetCategory().getCategoryName()+", ");
        if(asset.getAssetModel() != null)
            System.out.print("AssetModel: " + asset.getAssetModel()+", ");
        if(asset.getAssetDescription() != null)
            System.out.print("AssetDescription: " + asset.getAssetDescription()+", ");
        if(asset.getAssetLocation()!= null)
            System.out.print("AssetLocation: " + asset.getAssetLocation().getLocationName()+", ");
        System.out.print("AssetStatus: " + asset.getAssetStatus()+", ");
        System.out.print("AssetStatus Formatted: " + AssetStatus.getStatusName(asset.getAssetStatus())+", ");
        if(asset.getAssetOwner() instanceof User)
            System.out.print("AssetOwner (User) :" + (String) ((User) asset.getAssetOwner()).getName()+", ");
        else if(asset.getAssetOwner() instanceof Project)
            System.out.print("AssetOwner (Project) :" + (String) ((Project) asset.getAssetOwner()).getProjectName()+", ");
        if(asset.getAssetAcqisationDate() != null)
            System.out.print("AssetAcquisitionDate: " + simpleDateFormat.format(asset.getAssetAcqisationDate())+", ");
        if(asset.getExpiryDate() != null)
            System.out.print("AssetExpiryDate: " + simpleDateFormat.format(asset.getExpiryDate()));
    }
}
