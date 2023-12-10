package blackboxtest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_CategoryHandler {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        Object obj0 = new Object();
        Class<?> wildcardClass1 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        mobileclientassetmanagement.src.handler.CategoryHandler categoryHandler0 = new mobileclientassetmanagement.src.handler.CategoryHandler();
        Class<?> wildcardClass1 = categoryHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        mobileclientassetmanagement.src.handler.CategoryHandler categoryHandler0 = new mobileclientassetmanagement.src.handler.CategoryHandler();
        categoryHandler0.setCanRunAgain(true);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        System.setIn(new ByteArrayInputStream("1\nMobile Phone\nMobile Phone Description\n".getBytes()));
        mobileclientassetmanagement.src.handler.CategoryHandler categoryHandler0 = new mobileclientassetmanagement.src.handler.CategoryHandler();
        categoryHandler0.setCanRunAgain(false);
        categoryHandler0.execute();
        Class<?> wildcardClass2 = categoryHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        String testInput = "7\n";
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.CategoryHandler categoryHandler1 = new mobileclientassetmanagement.src.handler.CategoryHandler(scanner0);
        categoryHandler1.setCanRunAgain(false);
        categoryHandler1.execute();
    }
}

