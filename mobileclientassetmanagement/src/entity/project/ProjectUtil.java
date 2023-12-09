package entity.project;

import dbmanager.DataManager;
import util.AppUtil;
import util.Constants;
import util.imports.ImportFactory;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProjectUtil {
    public static final String[] EXPORT_HEADER = {"ProjectID", "ProjectName", "ProjectDescription", "ProjectOwner"};
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("projectID");
    public static Integer generateProjectID() {
        Integer projectID= 1;
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        Map.Entry<Integer, Project> lastEntry = null;
        for(Map.Entry<Integer, Project> projectEntry : projectDataMap.entrySet()){
            lastEntry = projectEntry;
        }
        projectID = lastEntry == null ? projectID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return projectID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "CI Bank\nCI Bank Description\n1\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForProjectData() {
        String resourcesFolder = "src/resources";
        String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, resourcesFolder, Constants.PROJECT_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleProjectImportForTest() {
        String resourcePath = getPathForProjectData();
        ImportFactory.getHandler(Constants.PROJECT).handleImport(resourcePath);
    }
}
