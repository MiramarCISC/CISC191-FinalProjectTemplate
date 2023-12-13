package edu.sdccd.cisc191;

import java.io.Serializable;
public class GroceryResponse implements Serializable {
    private GroceryRequest request;
    private double price;
    private int calories;
    public GroceryResponse(GroceryRequest request, double price, int calories){
        this.request = request;
        this.price = price;
        this.calories = calories;
    }
    public GroceryRequest getRequest() {
        return request;
    }
    public double getPrice() {
        return price;
    }
    public int getCalories(){
        return calories;
    }

}
