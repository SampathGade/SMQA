package mobileclientassetmanagement.src.entity.vendor;

import com.opencsv.CSVReader;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.util.imports.ImportInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class VendorImportHandler implements ImportInterface {
    @Override
    public void handleImport(String filePath) {
        Map<Integer, Vendor> vendorDataMap = DataManager.getVendorData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String vendorName = csvData[1];
                String vendorEmail = csvData[2];
                String vendorAddress = csvData[3];
                Integer vendorID = VendorUtil.generateVendorID();
                Vendor vendor = new Vendor(vendorID, vendorName, vendorEmail, vendorAddress, null);
                vendorDataMap.put(vendorID, vendor);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file"); }
    }
}
