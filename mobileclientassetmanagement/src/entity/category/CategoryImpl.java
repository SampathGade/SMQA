package mobileclientassetmanagement.src.entity.category;


import mobileclientassetmanagement.src.dbmanager.DataManager;

import java.util.List;
import java.util.Map;

public class CategoryImpl implements CategoryInterface {

    private Map<Integer, Category> categoryData;

    CategoryImpl() {
        this.categoryData = DataManager.getCategoryData();
    }


    @Override
    public void add(Category category) {
        categoryData.put(category.getCategoryID(), category);
    }

    @Override
    public void update(Integer categoryID, Category category) {
        categoryData.put(categoryID, category);
    }

    @Override
    public void delete(Integer categoryID) {
        categoryData.remove(categoryID);
    }
}
