package mobileclientassetmanagement.src.entity.category;

import java.util.List;

public interface CategoryInterface
{
    Category get(Integer categoryID);
    void add(Category category);
    void update(Integer categoryID, Category category);
    void delete(Integer categoryID);
    List<Category> getAll();
}
