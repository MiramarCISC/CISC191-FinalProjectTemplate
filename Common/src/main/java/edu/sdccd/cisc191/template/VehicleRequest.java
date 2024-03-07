package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VehicleRequest {
    private int year;
    private String make;
    private String model;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(VehicleRequest vehicle) throws Exception {
        return objectMapper.writeValueAsString(vehicle);
    }
    public static VehicleRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, VehicleRequest.class);
    }

    public VehicleRequest(){}
    public VehicleRequest(int year, String make, String model) {
        this.year = year;
        this.make = make;
        this.model = model;
    }

    @Override
    public String toString() {
        return "VehicleRequest{" +
                "year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}