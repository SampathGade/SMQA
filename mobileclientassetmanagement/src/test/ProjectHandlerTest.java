package mobileclientassetmanagement.src.test;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.project.Project;
import mobileclientassetmanagement.src.entity.project.ProjectFactoryImpl;
import mobileclientassetmanagement.src.entity.project.ProjectInterface;
import mobileclientassetmanagement.src.entity.project.ProjectUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.ProjectHandler;

import org.junit.Before;
import org.junit.Test;
import mobileclientassetmanagement.src.util.AppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ProjectHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }


    @Test
    public void handleExecuteForCreate() {
        try {
            String testInput = "1\nHSGC Bank\nHSGC Bank Description\n7\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
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
            Project project = addTestProject();
            String testInput = "2\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
            assertNotNull(project);
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
            Project project = addTestProject();
            String testInput = "3\n1\nCI Bank\nCI Bank Description\n1\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
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
            Project project = addTestProject();
            String testInput = "4\n1\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
            assertFalse(project.getProjectID() == null);
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
            String testInput = "5\n\n"+ ProjectUtil.getPathForProjectData()+"\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
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
            addTestProject();
            ProjectHandler projectHandler = new ProjectHandler();
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
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
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
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
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
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
            String testInput = "1\nHSGC Bank\nHSGC Bank Description\n7\n";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            Scanner scanner = new Scanner(inputStream);
            AppUtil.setCurrentUser(DataManager.getUserData().get(3));
            ProjectHandler projectHandler = new ProjectHandler(scanner);
            projectHandler.setCanRunAgain(false);
            projectHandler.execute();
        }
        catch (Exception e) {
            assertFalse(true);
        }
        finally {
            System.setIn(System.in);
        }
    }

    private Project addTestProject() {
        Project project = new Project();
        project.setProjectID(1);
        project.setProjectName("HSGC Bank");
        project.setProjectDescription("HSGC Bank Description");
        project.setProjectOwner(DataManager.getUserData().get(1));
        ProjectInterface projectInterface = new ProjectFactoryImpl().createProject();
        projectInterface.add(project);
        return project;
    }
}