package mobileclientassetmanagement.src.entity.asset;

import com.opencsv.CSVWriter;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.exports.ExportInterface;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

public class AssetExportHandler implements ExportInterface {

    @Override
    public void handleExport(Object exportData, String filePath) {
        filePath = (filePath != null && !filePath.isEmpty()) ? filePath : getFilePath();
        try(FileWriter fileWriter = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, Asset> assetExportData = (Map<Integer, Asset>) exportData;
            csvWriter.writeNext(AssetUtil.EXPORT_HEADER);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
            for(Map.Entry<Integer, Asset> entry : assetExportData.entrySet()){
                Asset asset = entry.getValue();
                String acquiredDate = simpleDateFormat.format(asset.getAssetAcqisationDate());
                String expiryDate = simpleDateFormat.format(asset.getExpiryDate());
                String[] data = {String.valueOf(asset.getAssetID()), asset.getAssetName(), asset.getAssetCategory().getCategoryName(), asset.getAssetModel(), asset.getAssetDescription(), asset.getAssetLocation().getLocationName(), AssetStatus.getStatusName(asset.getAssetStatus()), acquiredDate, expiryDate};
                csvWriter.writeNext(data);
            }
            System.out.println("CSV Exported Successfully");
        }
        catch (Exception e) { System.out.println("Exception occurred while exporting");}
    }

    private String getFilePath() {
        String desktopPath = AppUtil.getDesktopPath();
        String folderPath = desktopPath + "/" + "Asset";
        AppUtil.createFolder(folderPath);
        return folderPath+"/"+"AssetExport.csv";
    }
}
