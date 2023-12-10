package blackboxtest;


import mobileclientassetmanagement.src.entity.category.CategoryUtil;
import mobileclientassetmanagement.src.entity.location.LocationUtil;
import mobileclientassetmanagement.src.entity.project.ProjectUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0_AssetHandler {

    public static boolean debug = false;

    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        LocationUtil.handleLocationImportForTest();
        ProjectUtil.handleProjectImportForTest();
        CategoryUtil.handleCategoryImportForTest();
    }

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        System.setIn(new ByteArrayInputStream("1\nDell Monitor\n11\n1250\nDell Monitor Description\n1\n2025-12-12\n".getBytes()));
        mobileclientassetmanagement.src.handler.AssetHandler assetHandler0 = new mobileclientassetmanagement.src.handler.AssetHandler();
        assetHandler0.setCanRunAgain(false);
        assetHandler0.execute();
        System.setIn(System.in);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        Scanner scanner0 = null;
        InputStream inputStream = new ByteArrayInputStream("12\n".getBytes());
        scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.AssetHandler assetHandler1 = new mobileclientassetmanagement.src.handler.AssetHandler(scanner0);
        assetHandler1.setCanRunAgain(false);
        assetHandler1.execute();
        System.setIn(System.in);
    }
}

