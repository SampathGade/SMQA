package blackboxtest;

import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_ProjectHandler {

    public static boolean debug = false;

    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        InputStream inputStream = new ByteArrayInputStream("1\nHSGC Bank\nHSGC Bank Description\n7\n".getBytes());
        java.util.Scanner scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.ProjectHandler projectHandler1 = new mobileclientassetmanagement.src.handler.ProjectHandler(scanner0);
        projectHandler1.setCanRunAgain(false);
        projectHandler1.execute();
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
        mobileclientassetmanagement.src.handler.ProjectHandler projectHandler0 = new mobileclientassetmanagement.src.handler.ProjectHandler();
        java.lang.Class<?> wildcardClass1 = projectHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        mobileclientassetmanagement.src.handler.ProjectHandler projectHandler0 = new mobileclientassetmanagement.src.handler.ProjectHandler();
        projectHandler0.setCanRunAgain(true);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        System.setIn(new ByteArrayInputStream("7\n".getBytes()));
        mobileclientassetmanagement.src.handler.ProjectHandler projectHandler0 = new mobileclientassetmanagement.src.handler.ProjectHandler();
        projectHandler0.setCanRunAgain(false);
        projectHandler0.execute();
        java.lang.Class<?> wildcardClass2 = projectHandler0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass2);
    }
}

