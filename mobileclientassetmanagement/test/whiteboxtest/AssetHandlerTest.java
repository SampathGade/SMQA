package handler;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.*;
import mobileclientassetmanagement.src.entity.category.CategoryUtil;
import mobileclientassetmanagement.src.entity.location.LocationUtil;
import mobileclientassetmanagement.src.entity.project.ProjectUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.AssetHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AssetHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        LocationUtil.handleLocationImportForTest();
        ProjectUtil.handleProjectImportForTest();
        CategoryUtil.handleCategoryImportForTest();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nDell Monitor\n11\n1250\nDell Monitor Description\n1\n2025-12-12\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
            Asset asset = addTestAsset();
            String testInput = "2\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertNotNull(asset);
            assertEquals("1", asset.getAssetID().toString());
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
            addTestAsset();
            String testInput = "3\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
    public void handleExecuteForDelete() {
        try {
            Asset asset = addTestAsset();
            String testInput = "4\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertFalse(asset.getAssetID() == null);
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
    public void handleExecuteForImport() {
        try {
            String testInput = "5\n\n"+ AssetUtil.getPathForAssetData()+"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
            addTestAsset();
            AssetHandler assetHandler = new AssetHandler();
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
    public void handleExecuteForMoveAsset() {
        try {
            String testInput = "7\n1\n1\n";
            addTestAsset();
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertTrue("Asset Moved Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForAssignByUser() {
        try {
            String testInput = "8\n1\n1\n";
            addTestAsset();
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertTrue("Asset Assigned to User Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForAssignByProject() {
        try {
            String testInput = "9\n1\n1\n";
            addTestAsset();
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertTrue("Asset Assigned to Project Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForAssetExpiry() {
        try {
            String testInput = "10\n1\n";
            addTestAssetForProject();
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertTrue("Asset Marked as Expired", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForAssetDecommission() {
        try {
            String testInput = "11\n1\n";
            addTestAsset();
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertTrue("Asset Marked as Decommissioned", true);
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
            String testInput = "12\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
            String testInput = "13\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
            String testInput = "1\nDell Monitor\n11\n1250\nDell Monitor Description\n1\n2025-12-12\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
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
    public void handleExecuteForAssetUserRetrieve() {
        try {
            Asset asset = addTestAsset();
            String testInput = "1\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(2));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertNotNull(asset);
            assertEquals("1", asset.getAssetID().toString());
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForTechnicianRetrieve() {
        try {
            Asset asset = addTestAsset();
            String testInput = "1\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(4));
            AssetHandler assetHandler = new AssetHandler(scanner);
            assetHandler.setCanRunAgain(false);
            assetHandler.execute();
            assertNotNull(asset);
            assertEquals("1", asset.getAssetID().toString());
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private Asset addTestAsset() throws Exception{
      Asset asset = new Asset();
      asset.setAssetID(1);
      asset.setAssetName("Dell Monitor");
      asset.setAssetModel("1250");
      asset.setAssetDescription("Dell Monitor Description");
      asset.setAssetCategory(DataManager.getCategoryData().get(11));
      asset.setAssetLocation(DataManager.getLocationData().get(1));
      asset.setAssetAcqisationDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2023-12-12"));
      asset.setExpiryDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2025-12-12"));
      asset.setAssetStatus(AssetStatus.UNASSIGNED.getStatusCode());
      asset.setAssetOwner(DataManager.getUserData().get(1));
      AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
      assetInterface.add(asset);
      return asset;
    }

    private Asset addTestAssetForProject() throws Exception{
        Asset asset = new Asset();
        asset.setAssetID(1);
        asset.setAssetName("Dell Monitor");
        asset.setAssetModel("1250");
        asset.setAssetDescription("Dell Monitor Description");
        asset.setAssetCategory(DataManager.getCategoryData().get(11));
        asset.setAssetLocation(DataManager.getLocationData().get(1));
        asset.setAssetAcqisationDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2023-12-12"));
        asset.setExpiryDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2025-12-12"));
        asset.setAssetStatus(AssetStatus.UNASSIGNED.getStatusCode());
        asset.setAssetOwner(DataManager.getProjectData().get(1));
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.add(asset);
        return asset;
    }
}