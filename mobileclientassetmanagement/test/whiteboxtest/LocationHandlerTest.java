package whiteboxtest;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.location.Location;
import mobileclientassetmanagement.src.entity.location.LocationFactoryImpl;
import mobileclientassetmanagement.src.entity.location.LocationInterface;
import mobileclientassetmanagement.src.entity.location.LocationUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.LocationHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LocationHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nLondon\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForRetrieve() {
        try {
            String testInput = "2\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            Location location = addTestLocation();
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertNotNull(location);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForUpdate() {
        try {
            String testInput = "3\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestLocation();
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForDelete() {
        try {
            String testInput = "4\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            Location location = addTestLocation();
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertFalse( location.getLocationID() == null);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForImport() {
        try {
            String testInput = "5\n\n"+ LocationUtil.getPathForLocationData()+"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertTrue("Import SuccessFul", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForExport() {
        try {
            String testInput = "6\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestLocation();
            LocationHandler locationHandler = new LocationHandler();
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertTrue("Export SuccessFul", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForExit() {
        try {
            String testInput = "7\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForInvalidOption() {
        try {
            String testInput = "8\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForAssetManagerViewCreate() {
        try {
            String testInput = "1\nLondon\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForTechnicianViewRetrieve() {
        try {
            String testInput = "1\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(4));
            Location location = addTestLocation();
            LocationHandler locationHandler = new LocationHandler(scanner);
            locationHandler.setCanRunAgain(false);
            locationHandler.execute();
            assertNotNull(location);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private Location addTestLocation() {
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("London");
        LocationInterface locationInterface = new LocationFactoryImpl().createLocation();
        locationInterface.add(location);
        return location;
    }
}