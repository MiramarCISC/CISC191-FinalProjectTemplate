package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory("crimp", "wataaah", "bolt on", "dual tex",
                "moderate", "black", "small");
    }

    @Test
    void getHoldType() {
        assertEquals(inventory.getHoldType(), "crimp");
    }

    @Test
    void getManufacture() {
        assertEquals(inventory.getManufacture(), "wataaah");
    }

    @Test
    void getMountingOption() {
        assertEquals(inventory.getMountingOption(), "bolt on");
    }

    @Test
    void getTexture() {
        assertEquals(inventory.getTexture(), "dual tex");
    }

    @Test
    void getDifficulty() {
        assertEquals(inventory.getDifficulty(), "moderate");
    }

    @Test
    void getColor() {
        assertEquals(inventory.getColor(), "black");
    }

    @Test
    void getSize() {
        assertEquals(inventory.getSize(), "small");
    }
}
