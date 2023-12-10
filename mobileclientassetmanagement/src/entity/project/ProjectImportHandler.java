package mobileclientassetmanagement.src.entity.project;

import com.opencsv.CSVReader;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.role.UserRole;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.util.imports.ImportInterface;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class ProjectImportHandler implements ImportInterface {
    @Override
    public void handleImport(String filePath) {
        Map<Integer, Project> projectDataMap = DataManager.getProjectData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String projectName = csvData[1];
                String projectDescription = csvData[2];
                String projectOwnerStr = csvData[3];
                User projectOwner = handleUserImport(projectOwnerStr);
                Integer projectID = ProjectUtil.generateProjectID();
                Project project = new Project(projectID, projectName, projectDescription, projectOwner);
                projectDataMap.put(projectID, project);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file");}
    }

    private User handleUserImport(String userName) {
        Map<Integer, User> userDataMap = DataManager.getUserData();
        for(User user : userDataMap.values()) {
            if(user.getName().equals(userName)) {
                return user;
            }
        }

        // User Not Found
        Integer userID = UserUtil.generateUserID();
        User user = new User(userID, userName, "", UserRole.ASSET_USER, "", "");
        userDataMap.put(userID, user);
        return user;
    }
}
