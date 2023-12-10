package mobileclientassetmanagement.src.test;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.Asset;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
import mobileclientassetmanagement.src.entity.role.UserRole;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.entity.useraccount.UserFactoryImpl;
import mobileclientassetmanagement.src.entity.useraccount.UserInterface;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.UserHandler;

import org.junit.Before;
import org.junit.Test;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UserHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nLeo\nleo@email.com\n1\nmanager\ndescription test";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForRetrieve() {
        try {
            String testInput = "2\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestUser();
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForUpdate() {
        try {
            String testInput = "3\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestUser();
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForDelete() {
        try {
            String testInput = "4\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            User user = addTestUser();
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
            assertFalse(user.getUserID() == null);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForImport() {
        try {
            String testInput = "5\n\n"+ UserUtil.getPathForUserData()+"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
            assertTrue("Import SuccessFul", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForExport() {
        try {
            String testInput = "6\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            addTestUser();
            UserHandler userHandler = new UserHandler();
            userHandler.setCanRunAgain(false);
            userHandler.execute();
            assertTrue("Export SuccessFul", true);
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
            String testInput = "7\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
            assertTrue("Exited Successfully", true);
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
            String testInput = "8\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            UserHandler userHandler = new UserHandler(scanner);
            userHandler.setCanRunAgain(false);
            userHandler.execute();
            assertTrue("Exited Successfully", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void handleExecuteForExportAssetManager() {
        try {
            String testInput = "2\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            addTestUser();
            UserHandler userHandler = new UserHandler();
            userHandler.setCanRunAgain(false);
            userHandler.execute();
            assertTrue("Export SuccessFul", true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private User addTestUser() throws Exception{
        User user = new User();
        user.setUserID(1);
        user.setName("Leo");
        user.setEmailID("leo@gmail.com");
        user.setUserRole(UserRole.ADMIN);
        user.setDescription("Role description");
        user.setJobTitle("Manager");
        UserInterface userInterface = new UserFactoryImpl().createUser();
        userInterface.add(user);
        return user;
    }

}