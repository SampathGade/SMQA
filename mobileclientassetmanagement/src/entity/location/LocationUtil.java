package mobileclientassetmanagement.src.entity.location;
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

public class LocationUtil {
    public static final String[] EXPORT_HEADER = {"LocationID", "LocationName"};
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("locationID");
    public static Integer generateLocationID() {
        Integer locationID = 1;
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        Map.Entry<Integer, Location> lastEntry = null;
        for(Map.Entry<Integer, Location> locationEntry: locationDataMap.entrySet()){
            lastEntry = locationEntry;
        }
        locationID = lastEntry == null ? locationID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return locationID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "Leicester\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForLocationData() {
        String resourcesFolder = "src/resources";
        String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, resourcesFolder, Constants.LOCATION_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleLocationImportForTest() {
        String resourcePath = getPathForLocationData();
        ImportFactory.getHandler(Constants.LOCATION).handleImport(resourcePath);
    }
}
