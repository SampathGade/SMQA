package blackboxtest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_ProductHandler {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        mobileclientassetmanagement.src.handler.ProductHandler productHandler0 = new mobileclientassetmanagement.src.handler.ProductHandler();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        InputStream inputStream = new ByteArrayInputStream("7\n".getBytes());
        Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.ProductHandler productHandler1 = new mobileclientassetmanagement.src.handler.ProductHandler(scanner0);
        productHandler1.setCanRunAgain(false);
        productHandler1.execute();
    }
}

