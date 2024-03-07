package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerRequestTest {
    private VehicleRequest vehicleRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicleRequest = new VehicleRequest(1);
    }

    @org.junit.jupiter.api.Test
    void getCustomer() {
        assertEquals(vehicleRequest.toString(), "Customer[id=1]");
    }

    @org.junit.jupiter.api.Test
    void setCustomer() {
        assertEquals(vehicleRequest.toString(), "Customer[id=1]");
    }
}