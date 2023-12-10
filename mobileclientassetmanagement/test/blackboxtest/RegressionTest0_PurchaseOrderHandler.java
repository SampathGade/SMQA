package blackboxtest;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.product.ProductUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.entity.vendor.VendorUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_PurchaseOrderHandler {

    public static boolean debug = false;

    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        VendorUtil.handleVendorImport();
        ProductUtil.handleProductImport();
        DataManager.getVendorData().get(1).setAssociatedVendorProduct(Arrays.asList(DataManager.getProductData().get(1), DataManager.getProductData().get(2)));
    }

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        InputStream inputStream = new ByteArrayInputStream("1\n2023-12-12\nVictoria Avenue\n1\n1\n1\n-1".getBytes());
        java.util.Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.PurchaseOrderHandler purchaseOrderHandler1 = new mobileclientassetmanagement.src.handler.PurchaseOrderHandler(scanner0);
        purchaseOrderHandler1.setCanRunAgain(false);
        purchaseOrderHandler1.execute();
        purchaseOrderHandler1.execute();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        mobileclientassetmanagement.src.handler.PurchaseOrderHandler purchaseOrderHandler0 = new mobileclientassetmanagement.src.handler.PurchaseOrderHandler();
        java.lang.Class<?> wildcardClass1 = purchaseOrderHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        mobileclientassetmanagement.src.handler.PurchaseOrderHandler purchaseOrderHandler0 = new mobileclientassetmanagement.src.handler.PurchaseOrderHandler();
        purchaseOrderHandler0.setCanRunAgain(true);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        System.setIn(new ByteArrayInputStream("7\n".getBytes()));
        mobileclientassetmanagement.src.handler.PurchaseOrderHandler purchaseOrderHandler0 = new mobileclientassetmanagement.src.handler.PurchaseOrderHandler();
        purchaseOrderHandler0.execute();
        java.lang.Class<?> wildcardClass2 = purchaseOrderHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass2);
    }
}

