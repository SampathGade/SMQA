package mobileclientassetmanagement.src.util.imports;


import mobileclientassetmanagement.src.entity.asset.AssetImportHandler;
import mobileclientassetmanagement.src.entity.category.CategoryImportHandler;
import mobileclientassetmanagement.src.entity.location.LocationImportHandler;
import mobileclientassetmanagement.src.entity.product.ProductImportHandler;
import mobileclientassetmanagement.src.entity.project.ProjectImportHandler;
import mobileclientassetmanagement.src.entity.useraccount.UserImportHandler;
import mobileclientassetmanagement.src.entity.vendor.VendorImportHandler;

public class ImportFactory {
    public static ImportInterface getHandler(int type) {
        switch (type) {
            case 1:
                return new UserImportHandler();
            case 2:
                return new AssetImportHandler();
            case 6:
                return new CategoryImportHandler();
            case 7:
                return new LocationImportHandler();
            case 8:
                return new ProductImportHandler();
            case 9:
                return new ProjectImportHandler();
            case 10:
                return new VendorImportHandler();
            default:
                throw new IllegalArgumentException();
        }
    }
}
