package whiteboxtest;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
import mobileclientassetmanagement.src.entity.maintenancerequest.MaintenanceRequest;
import mobileclientassetmanagement.src.entity.maintenancerequest.MaintenanceRequestFactoryImpl;
import mobileclientassetmanagement.src.entity.maintenancerequest.MaintenanceRequestInterface;
import mobileclientassetmanagement.src.entity.maintenancerequest.MaintenanceRequestStatus;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.MaintenanceRequestHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaintenanceRequestHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        AssetUtil.handleAssetImportForFirstLogin();
    }

    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nMaintenance Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
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
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
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
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
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
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForViewOpenMaintenanceRequest() {
        try {
            String testInput = "5\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForViewAllMaintenanceRequest() {
        try {
            String testInput = "6\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForCloseMaintenanceRequest() {
        try {
            String testInput = "7\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
            assertTrue("Maintenance Request Closed Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }



    @Test
    public void handleExecuteForCommentMaintenanceRequest() {
        try {
            String testInput = "8\n1\nTest Comment 2\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addMaintenanceRequest();
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
            assertTrue("Commented Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForCreateAssetManager() {
        try {
            String testInput = "1\nMaintenance Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForCreateAssetUser() {
        try {
            String testInput = "1\nMaintenance Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(2));
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForCreateTechnician() {
        try {
            String testInput = "1\nAsset Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(4));
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
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
            String testInput = "9\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler();
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
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
            String testInput = "10\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            MaintenanceRequestHandler maintenanceRequestHandler = new MaintenanceRequestHandler(scanner);
            maintenanceRequestHandler.setCanRunAgain(false);
            maintenanceRequestHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    public MaintenanceRequest addMaintenanceRequest() throws Exception{
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setRequestID(1);
        maintenanceRequest.setRequestDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2023-12-12"));
        maintenanceRequest.setRequestStatus(MaintenanceRequestStatus.OPEN.getStatusCode());
        maintenanceRequest.setRequesterName(DataManager.getUserData().get(1));
        maintenanceRequest.setRequesterAssignee(DataManager.getUserData().get(1));
        maintenanceRequest.setRequestDescription("Maintenance Request Description");
        maintenanceRequest.setAssetAffected(Arrays.asList(DataManager.getAssetData().get(1), DataManager.getAssetData().get(2)));
        maintenanceRequest.setCommentList(Arrays.asList(new MaintenanceRequest.Comment("Test Comment 1", DataManager.getUserData().get(1))));
        MaintenanceRequestInterface maintenanceRequestInterface = new MaintenanceRequestFactoryImpl().createMaintenanceRequest();
        maintenanceRequestInterface.add(maintenanceRequest);
        return maintenanceRequest;
    }
}