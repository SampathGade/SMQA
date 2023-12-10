package blackboxtest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_MaintenanceRequestHandler {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        mobileclientassetmanagement.src.handler.MaintenanceRequestHandler maintenanceRequestHandler0 = new mobileclientassetmanagement.src.handler.MaintenanceRequestHandler();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        InputStream inputStream = new ByteArrayInputStream("9\n".getBytes());
        Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.MaintenanceRequestHandler maintenanceRequestHandler1 = new mobileclientassetmanagement.src.handler.MaintenanceRequestHandler(scanner0);
        maintenanceRequestHandler1.setCanRunAgain(false);
        maintenanceRequestHandler1.execute();
        maintenanceRequestHandler1.execute();
    }
}

