package mobileclientassetmanagement.src.util.exports;

import mobileclientassetmanagement.src.entity.asset.AssetExportHandler;
import mobileclientassetmanagement.src.entity.category.CategoryExportHandler;
import mobileclientassetmanagement.src.entity.location.LocationExportHandler;
import mobileclientassetmanagement.src.entity.product.ProductExportHandler;
import mobileclientassetmanagement.src.entity.project.ProjectExportHandler;
import mobileclientassetmanagement.src.entity.useraccount.UserExportHandler;
import mobileclientassetmanagement.src.entity.vendor.VendorExportHandler;

public class ExportFactory {
    public static ExportInterface getHandler(int type) {
        switch (type) {
            case 1:
                return new UserExportHandler();
            case 2:
                return new AssetExportHandler();
            case 6:
                return new CategoryExportHandler();
            case 7:
                return new LocationExportHandler();
            case 8:
                return new ProductExportHandler();
            case 9:
                return new ProjectExportHandler();
            case 10:
                return new VendorExportHandler();
            default:
                throw new IllegalArgumentException();
        }
    }
}
