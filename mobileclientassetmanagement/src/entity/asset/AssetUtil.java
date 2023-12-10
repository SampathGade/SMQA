package mobileclientassetmanagement.src.entity.asset;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import mobileclientassetmanagement.src.util.imports.ImportFactory;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AssetUtil {
    public static final Integer UNASSIGNED = 0;
    public static final Integer ASSIGNED = 1;
    public static final Integer EXPIRED = 2;
    public static final Integer DECOMMISSIONED = 3;

    public static final String UNASSIGNED_STRING = "UnAssigned";
    public static final String ASSIGNED_STRING = "Assigned";
    public static final String EXPIRED_STRING = "Expired";
    public static final String DECOMMISSIONED_STRING = "Decommissioned";

    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("assetID", "assetStatus", "assetOwner", "assetAcqisationDate");

    public static final String[] EXPORT_HEADER = {"AssetID", "AssetName", "AssetCategory", "AssetModel", "AssetDescription", "AssetLocation", "AssetStatus", "AssetAcquiredDate", "AssetAcquiredDate", "AssetOwner"};
    public static Integer generateAssetID() {
        Integer assetID = 1;
        Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
        Map.Entry<Integer, Asset> lastEntry = null;
        for(Map.Entry<Integer, Asset> assetEntry : assetDataMap.entrySet()){
            lastEntry = assetEntry;
        }
        assetID = lastEntry == null ? assetID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return assetID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "HP Monitor\n11\n1250\nHP Monitor Description\n1\n2025-12-12\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForAssetData() {
        String resourcesFolder = "src/resources";
        String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, resourcesFolder, Constants.ASSET_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleAssetImportForFirstLogin() {
        String resourcePath = getPathForAssetData();
        ImportFactory.getHandler(Constants.ASSET).handleImport(resourcePath);
    }
}
