package mobileclientassetmanagement.src.entity.useraccount;


import com.opencsv.CSVWriter;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.exports.ExportInterface;

import java.io.FileWriter;
import java.util.Map;

public class UserExportHandler implements ExportInterface {
    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();
        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, User> categoryExportData = (Map<Integer, User>) exportData;
            csvWriter.writeNext(UserUtil.EXPORT_HEADER);
            for(Map.Entry<Integer, User> entry : categoryExportData.entrySet()){
                User user = entry.getValue();
                String[] data = {String.valueOf(user.getUserID()), user.getName(), user.getEmailID(), user.getUserRole().getRoleName(), user.getJobTitle(), user.getDescription()};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting"); }
    }

    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "User";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"UserExport.csv";
    }
}
