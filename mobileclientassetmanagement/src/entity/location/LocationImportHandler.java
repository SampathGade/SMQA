package entity.location;

import com.opencsv.CSVReader;
import dbmanager.DataManager;
import util.imports.ImportInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class LocationImportHandler implements ImportInterface {
    @Override
    public void handleImport(String filePath) {
        Map<Integer, Location> locationDataMap = DataManager.getLocationData();
        try (FileReader fileReader = new FileReader(filePath); CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> csvDataList = csvReader.readAll();
            for(int i=0; i< csvDataList.size(); i++) {
                String[] csvData = csvDataList.get(i);
                String locationName = csvData[1];
                Integer locationID = LocationUtil.generateLocationID();
                Location location = new Location(locationID, locationName);
                locationDataMap.put(locationID, location);
            }
        }
        catch (Exception e) { System.out.println("Exception occurred while processing the file"); }
    }
}
