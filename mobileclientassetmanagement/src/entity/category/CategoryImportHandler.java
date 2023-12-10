package entity.category;

import com.opencsv.CSVReader;
import dbmanager.DataManager;
import util.imports.ImportInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class CategoryImportHandler implements ImportInterface {
    @Override
    public void handleImport(String filePath) {
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String categoryName = csvData[1];
                String categoryDescription = csvData[2];
                Integer categoryID = CategoryUtil.generateCategoryID();
                Category category = new Category(categoryID, categoryName, categoryDescription);
                categoryDataMap.put(categoryID, category);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file");}
    }
}
