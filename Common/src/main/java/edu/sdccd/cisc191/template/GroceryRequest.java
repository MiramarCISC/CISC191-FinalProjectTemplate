package edu.sdccd.cisc191;
import java.io.Serializable;
public class GroceryRequest implements Serializable {
    public String category;
    public String name;
    public GroceryRequest(String category, String name) {
        this.category = category;
        this.name = name;
    }
    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}