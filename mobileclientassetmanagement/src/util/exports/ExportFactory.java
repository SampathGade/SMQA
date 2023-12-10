package mobileclientassetmanagement.src.util.exports;

import mobileclientassetmanagement.src.entity.asset.AssetExportHandler;
import mobileclientassetmanagement.src.entity.location.LocationExportHandler;
import mobileclientassetmanagement.src.entity.product.ProductExportHandler;
import mobileclientassetmanagement.src.entity.project.ProjectExportHandler;
import mobileclientassetmanagement.src.entity.useraccount.UserExportHandler;

public class ExportFactory {
    public static ExportInterface getHandler(int type) {
        switch (type) {
            case 1:
                return new UserExportHandler();
            case 2:
                return new AssetExportHandler();
            case 7:
                return new LocationExportHandler();
            case 8:
                return new ProductExportHandler();
            case 9:
                return new ProjectExportHandler();
            default:
                throw new IllegalArgumentException();
        }
    }
}
