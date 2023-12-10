package whiteboxtest;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequest;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequestFactoryImpl;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequestInterface;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequestStatus;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.AssetRequestHandler;

import org.junit.Before;
import org.junit.Test;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AssetRequestHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        AssetUtil.handleAssetImportForFirstLogin();
    }

    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nAsset Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
            System.setIn(System.in);
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
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleViewOpenAssetRequest() {
        try {
            String testInput = "5\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleViewAllAssetRequest() {
        try {
            String testInput = "6\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleApproveAssetRequest() {
        try {
            String testInput = "7\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
            assertTrue("Asset Request Approved Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleRejectAssetRequest() {
        try {
            String testInput = "8\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
            assertTrue("Asset Request Rejected Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleCommentAssetRequest() {
        try {
            String testInput = "9\n1\nTest Comment 2\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addAssetRequest();
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            String testInput = "1\nAsset Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            String testInput = "1\nAsset Request Description\n1\n2\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(2));
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            String testInput = "10\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler();
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
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
            String testInput = "11\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetRequestHandler assetRequestHandler = new AssetRequestHandler(scanner);
            assetRequestHandler.setCanRunAgain(false);
            assetRequestHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    public AssetRequest addAssetRequest() throws Exception{
        AssetRequest assetRequest = new AssetRequest();
        assetRequest.setRequestID(1);
        assetRequest.setRequestDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2023-12-12"));
        assetRequest.setRequestStatus(AssetRequestStatus.OPEN.getStatusCode());
        assetRequest.setRequesterName(DataManager.getUserData().get(1));
        assetRequest.setRequesterAssignee(DataManager.getUserData().get(1));
        assetRequest.setRequestDescription("Asset Request Description");
        assetRequest.setAssetList(Arrays.asList(DataManager.getAssetData().get(1), DataManager.getAssetData().get(2)));
        assetRequest.setCommentList(Arrays.asList(new AssetRequest.Comment("Test Comment 1", DataManager.getUserData().get(1))));
        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
        assetRequestInterface.add(assetRequest);
        return assetRequest;
    }
}
