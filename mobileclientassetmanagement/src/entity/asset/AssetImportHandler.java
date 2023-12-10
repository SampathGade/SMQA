package mobileclientassetmanagement.src.entity.asset;


import com.opencsv.CSVReader;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.category.Category;
import mobileclientassetmanagement.src.entity.category.CategoryUtil;
import mobileclientassetmanagement.src.entity.location.Location;
import mobileclientassetmanagement.src.entity.location.LocationUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.imports.ImportInterface;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AssetImportHandler implements ImportInterface {

    @Override
    public void handleImport(String filePath) {
        Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String assetName = csvData[1];
                String assetCategoryStr = csvData[2];
                Category assetCategory = handleCategoryImport(assetCategoryStr);
                String assetModel = csvData[3];
                String assetDescription = csvData[4];
                String assetLocationStr = csvData[5];
                Location assetLocation = handleLocationImport(assetLocationStr);
                String assetAcquiredDateStr = csvData[6];
                Date assetAcqiredDate = simpleDateFormat.parse(assetAcquiredDateStr);
                String assetExpiryDateStr = csvData[7];
                Date assetExpiryDate = simpleDateFormat.parse(assetExpiryDateStr);
                Integer assetID = AssetUtil.generateAssetID();
                Asset asset = new Asset(assetID, assetName, assetCategory, assetModel, assetDescription, assetLocation, AssetUtil.UNASSIGNED, assetAcqiredDate, assetExpiryDate, null);
                assetDataMap.put(assetID, asset);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file");}
    }

    private Category handleCategoryImport(String assetCategory) {
        Map<Integer, Category> categoryDataMap = DataManager.getCategoryData();
        for(Category category : categoryDataMap.values()) {
            if(category.getCategoryName().equals(assetCategory)){
                return category;
            }
        }

        // Category Not Found
        Integer categoryID = CategoryUtil.generateCategoryID();
        Category category = new Category(categoryID, assetCategory, "");
        categoryDataMap.put(categoryID, category);
        return category;
    }

    private Location handleLocationImport(String assetLocation) {
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        for(Location location : locationDataMap.values()) {
            if(location.getLocationName().equals(assetLocation)) {
                return location;
            }
        }

        // Location Not Found
        Integer locationID = LocationUtil.generateLocationID();
        Location location = new Location(locationID, assetLocation);
        locationDataMap.put(locationID, location);
        return location;
    }
}
