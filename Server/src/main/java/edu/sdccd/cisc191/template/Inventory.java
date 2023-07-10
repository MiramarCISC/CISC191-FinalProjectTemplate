package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory implements Serializable{
    private Map<String, Car> carMap;

    public Inventory() {
        carMap = new HashMap<>();
    }

    public void addCar(Car car) {
        carMap.put(car.getMake() + car.getModel(), car);
    }

    public Car getCar(String make, String model) {
        return carMap.get(make + model);
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(carMap.values());
    }

    public void removeCar(String make, String model) {
        carMap.remove(make + model);
    }

    public int getSize() {
        return carMap.size();
    }
}
