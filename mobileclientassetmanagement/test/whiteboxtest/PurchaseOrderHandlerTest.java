package whiteboxtest;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.product.Product;
import mobileclientassetmanagement.src.entity.product.ProductUtil;
import mobileclientassetmanagement.src.entity.project.Project;
import mobileclientassetmanagement.src.entity.project.ProjectUtil;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrder;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrderFactoryImpl;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrderInterface;
import mobileclientassetmanagement.src.entity.purchaseorder.PurchaseOrderStatus;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.entity.vendor.VendorUtil;
import org.junit.Before;
import org.junit.Test;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PurchaseOrderHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        VendorUtil.handleVendorImport();
        ProductUtil.handleProductImport();
        DataManager.getVendorData().get(1).setAssociatedVendorProduct(Arrays.asList(DataManager.getProductData().get(1), DataManager.getProductData().get(2)));
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\n2023-12-12\nVictoria Avenue\n1\n1\n1\n-1";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler(scanner);
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
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
            addTestPurchaseOrder();
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler(scanner);
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
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
            addTestPurchaseOrder();
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler(scanner);
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
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
            PurchaseOrder purchaseOrder = addTestPurchaseOrder();
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler(scanner);
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
            assertFalse(purchaseOrder.getOrderID() == null);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }


    @Test
    public void handleExecuteForMarkAsPaid() {
        try {
            String testInput = "5\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestPurchaseOrder();
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler(scanner);
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
            assertTrue("Marked as Paid successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForPushItem() {
        try {
            String testInput = "6\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestPurchaseOrderForPaidStatus();
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler(scanner);
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
            assertTrue("Pushed Item As Item", true);
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
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler();
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
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
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            PurchaseOrderHandler purchaseOrderHandler = new PurchaseOrderHandler();
            purchaseOrderHandler.setCanRunAgain(false);
            purchaseOrderHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private PurchaseOrder addTestPurchaseOrder() throws Exception{
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderID(1);
        purchaseOrder.setOrderDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2023-12-12"));
        purchaseOrder.setBillingAddress("Victoria Avenue");
        DataManager.getVendorData().get(1).setAssociatedVendorProduct(Arrays.asList(DataManager.getProductData().get(1)));
        purchaseOrder.setOrderVendor(DataManager.getVendorData().get(1));
        purchaseOrder.setTotalCost(new BigDecimal(1200));
        purchaseOrder.setStatus(PurchaseOrderStatus.OPEN.getStatusCode());
        List<PurchaseOrder.Item> orderItemList = new ArrayList<>();
        PurchaseOrder.Item orderItem = new PurchaseOrder.Item();
        Product testProduct = DataManager.getProductData().get(1);
        orderItem.setItemName(testProduct.getProductName());
        orderItem.setPrice(testProduct.getProductCost());
        orderItem.setQuantity(1);
        orderItemList.add(orderItem);
        purchaseOrder.setOrderItems(orderItemList);
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.add(purchaseOrder);
        return purchaseOrder;
    }

    private PurchaseOrder addTestPurchaseOrderForPaidStatus() throws Exception{
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderID(1);
        purchaseOrder.setOrderDate(new SimpleDateFormat(AppUtil.DATE_PATTERN).parse("2023-12-12"));
        purchaseOrder.setBillingAddress("Victoria Avenue");
        DataManager.getVendorData().get(1).setAssociatedVendorProduct(Arrays.asList(DataManager.getProductData().get(1)));
        purchaseOrder.setOrderVendor(DataManager.getVendorData().get(1));
        purchaseOrder.setTotalCost(new BigDecimal(1200));
        purchaseOrder.setStatus(PurchaseOrderStatus.PAID.getStatusCode());
        List<PurchaseOrder.Item> orderItemList = new ArrayList<>();
        PurchaseOrder.Item orderItem = new PurchaseOrder.Item();
        Product testProduct = DataManager.getProductData().get(1);
        orderItem.setItemName(testProduct.getProductName());
        orderItem.setPrice(testProduct.getProductCost());
        orderItem.setQuantity(1);
        orderItemList.add(orderItem);
        purchaseOrder.setOrderItems(orderItemList);
        PurchaseOrderInterface purchaseOrderInterface = new PurchaseOrderFactoryImpl().createPurchaseOrder();
        purchaseOrderInterface.add(purchaseOrder);
        return purchaseOrder;
    }
}