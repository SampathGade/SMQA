package mobileclientassetmanagement.src.entity.project;

import com.opencsv.CSVWriter;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.exports.ExportInterface;

import java.io.FileWriter;
import java.util.Map;

public class ProjectExportHandler implements ExportInterface {
    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();
        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, Project> projectExportData = (Map<Integer, Project>) exportData;
            csvWriter.writeNext(ProjectUtil.EXPORT_HEADER);
            for(Map.Entry<Integer, Project> entry : projectExportData.entrySet()){
                Project project = entry.getValue();
                String[] data = {String.valueOf(project.getProjectID()), project.getProjectName(), project.getProjectDescription(), project.getProjectOwner().getName()};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting");}
    }

    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "Project";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"ProjectExport.csv";
    }
}
