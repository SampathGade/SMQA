package mobileclientassetmanagement.src.entity.category;

import com.opencsv.CSVWriter;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.exports.ExportInterface;
import java.io.FileWriter;
import java.util.Map;

public class CategoryExportHandler implements ExportInterface {
    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();
        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, Category> categoryExportData = (Map<Integer, Category>) exportData;
            csvWriter.writeNext(CategoryUtil.EXPORT_HEADER);
            for(Map.Entry<Integer, Category> entry : categoryExportData.entrySet()){
                Category category = entry.getValue();
                String[] data = {String.valueOf(category.getCategoryID()), category.getCategoryName(), category.getCategoryDescription()};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting");}
    }

    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "Category";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"CategoryExport.csv";
    }
}
