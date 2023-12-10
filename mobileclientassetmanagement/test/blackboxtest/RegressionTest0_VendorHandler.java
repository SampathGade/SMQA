package blackboxtest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_VendorHandler {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        mobileclientassetmanagement.src.handler.VendorHandler vendorHandler0 = new mobileclientassetmanagement.src.handler.VendorHandler();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        java.util.Scanner scanner0 = null;
        mobileclientassetmanagement.src.handler.VendorHandler vendorHandler1 = new mobileclientassetmanagement.src.handler.VendorHandler(scanner0);
    }
}

