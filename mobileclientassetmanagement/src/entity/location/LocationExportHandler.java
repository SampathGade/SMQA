package entity.location;

import com.opencsv.CSVWriter;
import util.AppUtil;
import util.exports.ExportInterface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Map;

public class LocationExportHandler implements ExportInterface {
    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();
        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, Location> locationExportData = (Map<Integer, Location>) exportData;
            csvWriter.writeNext(LocationUtil.EXPORT_HEADER);
            for(Map.Entry<Integer, Location> entry : locationExportData.entrySet()){
                Location location = entry.getValue();
                String[] data = {String.valueOf(location.getLocationID()), location.getLocationName(), "", "", "", "", ""};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting");}
    }

    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "Location";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"LocationExport.csv";
    }
}
