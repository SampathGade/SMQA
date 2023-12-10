package mobileclientassetmanagement.src.entity.vendor;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.vendor.Vendor;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;
import mobileclientassetmanagement.src.util.imports.ImportFactory;

public class VendorUtil {
    public static final String[] EXPORT_HEADER = {"VendorID", "VendorName", "VendorEmail", "VendorAddress"};
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("vendorID", "associatedVendorProduct");
    public static Integer generateVendorID() {
        Integer vendorID = 1;
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        Map.Entry<Integer, Vendor> lastEntry = null;
        for(Map.Entry<Integer, Vendor> vendorEntry: vendorDataMap.entrySet()){
            lastEntry = vendorEntry;
        }
        vendorID = lastEntry == null ? vendorID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return vendorID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "XYZ Solutions Test\nxyztest@example.com\n456 Tech Avenue test\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForVendorData() {
        return Paths.get("src", "resources", Constants.VENDOR_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleVendorImport() {
        String resourcePath = getPathForVendorData();
        ImportFactory.getHandler(Constants.VENDOR).handleImport(resourcePath);
    }

}
