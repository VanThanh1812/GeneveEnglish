package vanthanh.com.model;

/**
 * Created by vanthanhbk on 27/11/2016.
 */

public class Category {
    public String id;
    public String category;

    public Category() {
        initCategory();
    }

    private void initCategory() {
        this.id = "0";
        this.category="Null";
    }

    public Category(String id, String category) {
        this.id = id;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
