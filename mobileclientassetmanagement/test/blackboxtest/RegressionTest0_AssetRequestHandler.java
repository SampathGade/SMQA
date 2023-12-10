package blackboxtest;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
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
public class RegressionTest0_AssetRequestHandler {

    public static boolean debug = false;

    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
        AssetUtil.handleAssetImportForFirstLogin();
    }

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        System.setIn(new ByteArrayInputStream("1\nAsset Request Description\n1\n2\n-1\n".getBytes()));
        AppUtil.setCurrentUser(DataManager.getUserData().get(1));
        mobileclientassetmanagement.src.handler.AssetRequestHandler assetRequestHandler0 = new mobileclientassetmanagement.src.handler.AssetRequestHandler();
        assetRequestHandler0.setCanRunAgain(false);
        assetRequestHandler0.execute();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        java.util.Scanner scanner0 = null;
        InputStream inputStream = new ByteArrayInputStream("10\n".getBytes());
        scanner0 = new Scanner(inputStream);
        mobileclientassetmanagement.src.handler.AssetRequestHandler assetRequestHandler1 = new mobileclientassetmanagement.src.handler.AssetRequestHandler(scanner0);
        assetRequestHandler1.setCanRunAgain(false);
        assetRequestHandler1.execute();
    }
}

