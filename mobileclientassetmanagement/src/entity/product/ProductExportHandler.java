package entity.product;

import com.opencsv.CSVWriter;
import util.AppUtil;
import util.exports.ExportInterface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Map;

public class ProductExportHandler implements ExportInterface {
    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();
        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, Product> productExportData = (Map<Integer, Product>) exportData;
            csvWriter.writeNext(ProductUtil.EXPORT_HEADER);
            for(Map.Entry<Integer, Product> entry : productExportData.entrySet()){
                Product product = entry.getValue();
                String[] data = {String.valueOf(product.getProductID()), product.getProductName(), product.getProductDescription(), product.getProductCost().toString()};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting"); }
    }

    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "Product";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"ProductExport.csv";
    }
}
