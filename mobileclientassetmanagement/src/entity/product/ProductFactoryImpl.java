package entity.product;

public class ProductFactoryImpl implements ProductFactory{
    @Override
    public ProductInterface createProduct() {
        return new ProductImpl();
    }
}
