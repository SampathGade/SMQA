package whiteboxtest;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.product.Product;
import mobileclientassetmanagement.src.entity.product.ProductFactoryImpl;
import mobileclientassetmanagement.src.entity.product.ProductInterface;
import mobileclientassetmanagement.src.entity.product.ProductUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.ProductHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ProductHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nPrinter\nHigh-speed printer for office use\n300\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
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
            Product product = addTestProduct();
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
            assertNotNull(product);
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
            addTestProduct();
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
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
            Product product = addTestProduct();
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
            assertFalse( product.getProductID() == null);
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
            String testInput = "5\n\n"+ ProductUtil.getPathForProductData() +"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
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
            addTestProduct();
            ProductHandler productHandler = new ProductHandler();
            productHandler.setCanRunAgain(false);
            productHandler.execute();
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
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
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
            ProductHandler productHandler = new ProductHandler(scanner);
            productHandler.setCanRunAgain(false);
            productHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private Product addTestProduct() {
        Product product = new Product();
        product.setProductID(1);
        product.setProductName("Printer");
        product.setProductDescription("High-speed printer for office use");
        product.setProductCost(new BigDecimal(300));
        ProductInterface productInterface = new ProductFactoryImpl().createProduct();
        productInterface.add(product);
        return product;
    }
}