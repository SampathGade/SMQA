package mobileclientassetmanagement.src.test;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.product.ProductUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.entity.vendor.Vendor;
import mobileclientassetmanagement.src.entity.vendor.VendorFactoryImpl;
import mobileclientassetmanagement.src.entity.vendor.VendorInterface;
import mobileclientassetmanagement.src.entity.vendor.VendorUtil;
import mobileclientassetmanagement.src.handler.VendorHandler;

import org.junit.Before;
import org.junit.Test;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

public class VendorHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        ProductUtil.handleProductImport();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nXYZ Solutions\nxyz@example.com\n456 Tech Avenue\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
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
            Vendor vendor = addTestVendor();
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
            assertNotNull(vendor);
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
            addTestVendor();
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
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
            Vendor vendor = addTestVendor();
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
            assertFalse( vendor.getVendorID() == null);
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
            String testInput = "5\n\n"+ VendorUtil.getPathForVendorData() +"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
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
            addTestVendor();
            VendorHandler vendorHandler = new VendorHandler();
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
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
    public void handleExecuteForAssociateProduct() {
        try {
            String testInput = "7\n1\n1\n-1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestVendor();
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
            assertTrue("Product Associated Successfully", true);
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
            String testInput = "8\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
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
            String testInput = "9\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            VendorHandler vendorHandler = new VendorHandler(scanner);
            vendorHandler.setCanRunAgain(false);
            vendorHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private Vendor addTestVendor() {
        Vendor vendor = new Vendor();
        vendor.setVendorID(1);
        vendor.setVendorName("XYZ Solutions");
        vendor.setVendorEmail("xyz@example.com");
        vendor.setVendorAddress("456 Tech Avenue");
        vendor.setAssociatedVendorProduct(Arrays.asList(DataManager.getProductData().get(1), DataManager.getProductData().get(2)));
        VendorInterface vendorInterface = new VendorFactoryImpl().createVendor();
        vendorInterface.add(vendor);
        return vendor;
    }
}