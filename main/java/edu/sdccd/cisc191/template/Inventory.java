package edu.sdccd.cisc191.template;

import javafx.beans.property.SimpleStringProperty;

public class Inventory {
    private final SimpleStringProperty holdType;
    private final SimpleStringProperty manufacture;
    private final SimpleStringProperty mountingOption;
    private final SimpleStringProperty texture;
    private final SimpleStringProperty difficulty;
    private final SimpleStringProperty color;
    private final SimpleStringProperty size;

    Inventory(String holdType, String manufacture, String mountingOption, String texture,
              String difficulty, String color, String size) {
        this.holdType = new SimpleStringProperty(holdType);
        this.manufacture = new SimpleStringProperty(manufacture);
        this.mountingOption = new SimpleStringProperty(mountingOption);
        this.texture = new SimpleStringProperty(texture);
        this.difficulty = new SimpleStringProperty(difficulty);
        this.color = new SimpleStringProperty(color);
        this.size = new SimpleStringProperty(size);
    }

    public String getHoldType() {
        return holdType.get();
    }

    public void setHoldType(String holdValue) {
        holdType.set(holdValue);
    }

    public String getManufacture() {
        return manufacture.get();
    }

    public void setManufacture(String manufactureValue) {
        manufacture.set(manufactureValue);
    }

    public String getMountingOption() {
        return mountingOption.get();
    }

    public void setMountingOption(String mountingValue) {
        mountingOption.set(mountingValue);
    }

    public String getTexture() {
        return texture.get();
    }

    public void setTexture(String textureValue) {
        texture.set(textureValue);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public void setDifficulty(String difficultyValue) {
        difficulty.set(difficultyValue);
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String colorValue) {
        color.set(colorValue);
    }

    public String getSize() {
        return size.get();
    }

    public void setSize(String sizeValue) {
        size.set(sizeValue);
    }
}