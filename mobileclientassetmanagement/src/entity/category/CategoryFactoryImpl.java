package mobileclientassetmanagement.src.entity.category;

public class CategoryFactoryImpl implements CategoryFactory{
    @Override
    public CategoryInterface createCategory() {
        return new CategoryImpl();
    }
}
