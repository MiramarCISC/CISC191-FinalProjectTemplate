package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.*;

class VehicleResponseTest {
    private VehicleResponse vehicleResponse;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicleResponse = new VehicleResponse(1, "Test", "User");
    }

    @org.junit.jupiter.api.Test
    void getCustomer() {
        assertEquals(vehicleResponse.toString(), "Customer[id=1, firstName='Test', lastName='User']");
    }

    @org.junit.jupiter.api.Test
    void setCustomer() {
        vehicleResponse.setFirstName("User");
        vehicleResponse.setLastName("Test");
        assertEquals(vehicleResponse.toString(), "Customer[id=1, firstName='User', lastName='Test']");
    }
}