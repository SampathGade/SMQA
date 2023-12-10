package mobileclientassetmanagement.src.entity.vendor;

import com.opencsv.CSVWriter;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.exports.ExportInterface;

import java.io.FileWriter;
import java.util.Map;

public class VendorExportHandler implements ExportInterface {

    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();

        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, Vendor> vendorExportData = (Map<Integer, Vendor>) exportData;
            csvWriter.writeNext(VendorUtil.EXPORT_HEADER);
            for(Map.Entry<Integer, Vendor> entry : vendorExportData.entrySet()){
                Vendor vendor = entry.getValue();
                String[] data = {String.valueOf(vendor.getVendorID()), vendor.getVendorName(), vendor.getVendorEmail(), vendor.getVendorAddress()};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting"); }
    }
    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "Vendor";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"VendorExport.csv";
    }
}
