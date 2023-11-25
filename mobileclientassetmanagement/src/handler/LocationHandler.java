package handler;

import dbmanager.DataManager;
import entity.category.CategoryUtil;
import entity.location.Location;
import entity.location.LocationFactoryImpl;
import entity.location.LocationInterface;
import entity.location.LocationUtil;
import entity.useraccount.User;
import util.AccessUtil;
import util.AppUtil;
import util.Constants;
import util.exports.ExportFactory;
import util.imports.ImportFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class LocationHandler implements Handler {

    private Map<Integer, String> locationHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }

    public LocationHandler() {
        this(null);
    }
    public LocationHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            locationHandlerMap = AccessUtil.ADMIN_LOCATION_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            locationHandlerMap = AccessUtil.ASSET_MANAGER_LOCATION_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_TECHNICIAN_STRING)) {
            locationHandlerMap = AccessUtil.TECHNICIAN_LOCATION_MODULE_MAP;
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
                if(!locationHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if(locationHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if(locationHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if(locationHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if(locationHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(locationHandlerMap.get(option).startsWith("Import")) {
                    handleImport(scanner);
                }
                else if(locationHandlerMap.get(option).startsWith("Export")) {
                    handleExport();
                }
                else if(locationHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer"); }
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : locationHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Location Creation....");
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        Location location = new Location();
        handleFieldInput(location);
        location.setLocationID(LocationUtil.generateLocationID());
        Integer locationID = location.getLocationID();
        LocationInterface locationInterface = new LocationFactoryImpl().createLocation();
        locationInterface.add(location);
        System.out.println("Location Created Successfully!!!!");
        displayLocationDetailsVertical(locationDataMap.get(locationID));
    }

    private Location handleRetrieve() {
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        displayAllLocations();
        int providedID = scanner.nextInt();
        Location providedLocation = locationDataMap.get(providedID);
        System.out.println("You have chosen the following Location");
        displayLocationDetailsVertical(providedLocation);
        return providedLocation;
    }

    private void handleUpdate() {
        Location locationToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = LocationUtil.getTestInputForUpdate();
        }
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        handleFieldInput(locationToBeUpdated);
        Integer locationID = locationToBeUpdated.getLocationID();
        LocationInterface locationInterface = new LocationFactoryImpl().createLocation();
        locationInterface.update(locationID, locationToBeUpdated);
        System.out.println("Location Updated Successfully!!!!");
        displayLocationDetailsVertical(locationDataMap.get(locationID));
    }

    private void handleDelete() {
        Location locationToBeDeleted = handleRetrieve();
        Integer locationID = locationToBeDeleted.getLocationID();
        LocationInterface locationInterface = new LocationFactoryImpl().createLocation();
        locationInterface.delete(locationID);
        System.out.println("Location Deleted Successfully!!!!");
    }

    private void displayAllLocations() {
        System.out.println("Displaying All Locations:");
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        for(Map.Entry<Integer, Location> locationEntry : locationDataMap.entrySet()){
            int locationID = locationEntry.getKey();
            Location location = locationEntry.getValue();
            System.out.println(locationID);
            displayLocationDetailsHorizotal(location);
        }
    }

    private void handleFieldInput(Location location) {
        try {
            Field[] locationFields = Location.class.getDeclaredFields();
            for(Field field : locationFields) {
                if(LocationUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == String.class) {
                    String input = scanner.nextLine();
                    field.set(location, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input"); }
    }

    private void handleImport(Scanner scanner) {
        System.out.println("Please provide Absolute Path to Import");
        scanner.nextLine();
        String filePath = scanner.nextLine();
        ImportFactory.getHandler(Constants.LOCATION).handleImport(filePath);
        System.out.println("Import Successfully Completed");
    }

    private void handleExport() {
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        ExportFactory.getHandler(Constants.LOCATION).handleExport(locationDataMap, "");
        System.out.println("Export Completed");
    }

    private static void displayLocationDetailsVertical(Location location) {
        System.out.println("\nLocation details:");
        System.out.println("LocationID: " +location.getLocationID());
        System.out.println("LocationName: " +location.getLocationName());
    }

    private static void displayLocationDetailsHorizotal(Location location) {
        System.out.print("LocationID: " +location.getLocationID()+", ");
        System.out.print("LocationName: " +location.getLocationName()+", ");
    }
}
