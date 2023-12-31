package mobileclientassetmanagement.src.entity.product;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import java.util.Map;

public class ProductImpl implements ProductInterface{
    private Map<Integer, Product> productData;

    ProductImpl() {
        this.productData = DataManager.getProductData();
    }

    @Override
    public void add(Product product) {
        productData.put(product.getProductID(), product);
    }

    @Override
    public void update(Integer productID, Product product) {
       productData.put(productID,product);
    }

    @Override
    public void delete(Integer productID) {
      productData.remove(productID);
    }
}
