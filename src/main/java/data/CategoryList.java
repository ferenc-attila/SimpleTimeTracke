package data;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        this.categoryList.add(category);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
