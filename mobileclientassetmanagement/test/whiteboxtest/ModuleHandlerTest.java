package whiteboxtest;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.ModuleHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;

public class ModuleHandlerTest {

    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }

    @Test
    public void handleExecuteForModuleAdminView() {
        try {
            String testInput = "2\n12\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ModuleHandler moduleHandler = new ModuleHandler(scanner);
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForModuleAssetManagerView() {
        try {
            String testInput = "2\n11\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            ModuleHandler moduleHandler = new ModuleHandler(scanner);
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForModuleAssetUserView() {
        try {
            String testInput = "1\n2\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(2));
            ModuleHandler moduleHandler = new ModuleHandler(scanner);
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForModuleTechnicianView() {
        try {
            String testInput = "1\n3\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(4));
            ModuleHandler moduleHandler = new ModuleHandler(scanner);
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForLogout() {
        try {
            String testInput = "12\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ModuleHandler moduleHandler = new ModuleHandler(scanner);
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForExit() {
        try {
            String testInput = "13\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ModuleHandler moduleHandler = new ModuleHandler(scanner);
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForInvalidOption() {
        try {
            String testInput = "14\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ModuleHandler moduleHandler = new ModuleHandler();
            moduleHandler.setCanRunAgain(false);
            moduleHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }
}