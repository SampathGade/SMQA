package blackboxtest;

import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_ModuleHandler {

    public static boolean debug = false;
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
    }
    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        mobileclientassetmanagement.src.handler.ModuleHandler moduleHandler0 = new mobileclientassetmanagement.src.handler.ModuleHandler();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        InputStream inputStream = new ByteArrayInputStream("2\n12\n".getBytes());
        Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.ModuleHandler moduleHandler1 = new mobileclientassetmanagement.src.handler.ModuleHandler(scanner0);
        moduleHandler1.setCanRunAgain(false);
        moduleHandler1.execute();
    }
}

