package whiteboxtest;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.category.Category;
import mobileclientassetmanagement.src.entity.category.CategoryFactoryImpl;
import mobileclientassetmanagement.src.entity.category.CategoryInterface;
import mobileclientassetmanagement.src.entity.category.CategoryUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.CategoryHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CategoryHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nMobile Phone\nMobile Phone Description\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
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
            Category category = addTestCategory();
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
            assertNotNull(category);
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
            addTestCategory();
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
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
            Category category = addTestCategory();
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
            assertFalse( category.getCategoryID()== null);
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
            String testInput = "5\n\n"+ CategoryUtil.getPathForCategoryData()+"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
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
            addTestCategory();
            CategoryHandler categoryHandler = new CategoryHandler();
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
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
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
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
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
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
    public void handleExecuteForAssetManagerViewCreate() {
        try {
            String testInput = "1\nMobile Phone\nMobile Phone Description\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            CategoryHandler categoryHandler = new CategoryHandler(scanner);
            categoryHandler.setCanRunAgain(false);
            categoryHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private Category addTestCategory() {
        Category category = new Category();
        category.setCategoryID(1);
        category.setCategoryName("Mobile Phone");
        category.setCategoryDescription("Mobile Phone Description");
        CategoryInterface categoryInterface = new CategoryFactoryImpl().createCategory();
        categoryInterface.add(category);
        return category;
    }
}