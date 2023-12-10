package blackboxtest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_UserHandler {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        mobileclientassetmanagement.src.handler.UserHandler userHandler0 = new mobileclientassetmanagement.src.handler.UserHandler();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        InputStream inputStream = new ByteArrayInputStream("1\nLeo\nleo@email.com\n1\nmanager\ndescription test".getBytes());
        java.util.Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.UserHandler userHandler1 = new mobileclientassetmanagement.src.handler.UserHandler(scanner0);
        userHandler1.setCanRunAgain(false);
        userHandler1.execute();
    }
}

