package edu.sdccd.cisc191;

import java.util.Objects;

public class GroceryItem {
    public String name;
    public double price;

    public GroceryItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GroceryItem otherItem = (GroceryItem) obj;
        return Double.compare(otherItem.price, price) == 0 && Objects.equals(name, otherItem.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public int compareTo(GroceryItem otherItem) {
        // Compare items based on their names
        return this.name.compareTo(otherItem.name);
    }
}
