package entity.product;

import java.util.List;

public interface ProductInterface {
    void add(Product product);
    void update(Integer productID, Product product);
    void delete(Integer productID);
}
