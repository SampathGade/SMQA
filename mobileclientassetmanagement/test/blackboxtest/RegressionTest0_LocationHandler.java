package blackboxtest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_LocationHandler {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        Scanner scanner0 = null;
        mobileclientassetmanagement.src.handler.LocationHandler locationHandler1 = new mobileclientassetmanagement.src.handler.LocationHandler(scanner0);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        System.setIn(new ByteArrayInputStream("1\nLondon\n".getBytes()));
        mobileclientassetmanagement.src.handler.LocationHandler locationHandler0 = new mobileclientassetmanagement.src.handler.LocationHandler();
        locationHandler0.setCanRunAgain(false);
        locationHandler0.execute();
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        Object obj0 = new Object();
        Class<?> wildcardClass1 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        mobileclientassetmanagement.src.handler.LocationHandler locationHandler0 = new mobileclientassetmanagement.src.handler.LocationHandler();
        Class<?> wildcardClass1 = locationHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        System.setIn(new ByteArrayInputStream("7\n".getBytes()));
        mobileclientassetmanagement.src.handler.LocationHandler locationHandler0 = new mobileclientassetmanagement.src.handler.LocationHandler();
        locationHandler0.setCanRunAgain(true);
        locationHandler0.execute();
    }
}

