package entity.product;

import com.opencsv.CSVReader;
import dbmanager.DataManager;
import util.imports.ImportInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductImportHandler implements ImportInterface {
    @Override
    public void handleImport(String filePath) {
        Map<Integer, Product> productDataMap = DataManager.getProductData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String productName = csvData[1];
                String productDescription = csvData[2];
                String productCost = csvData[3];
                Integer productID = ProductUtil.generateProductID();
                Product product = new Product(productID, productName, productDescription, new BigDecimal(productCost));
                productDataMap.put(productID, product);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file"); }
    }
}
