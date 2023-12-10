package mobileclientassetmanagement.src.entity.useraccount;


import com.opencsv.CSVReader;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.role.UserRole;
import mobileclientassetmanagement.src.util.imports.ImportInterface;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class UserImportHandler implements ImportInterface {

    @Override
    public void handleImport(String filePath) {
        Map<Integer, User> userDataMap = DataManager.getUserData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String userName = csvData[1];
                String emailID = csvData[2];
                String userRoleStr = csvData[3];
                String jobTitle = csvData[4];
                String jobDescription = csvData[5];
                Integer userID = UserUtil.generateUserID();
                User user = new User(userID, userName, emailID, UserRole.getCode(userRoleStr), jobTitle, jobDescription);
                userDataMap.put(userID, user);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file"); }
    }
}
