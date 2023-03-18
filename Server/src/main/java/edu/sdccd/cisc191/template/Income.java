package edu.sdccd.cisc191.template;

public class Income extends Entry {
    public Income (String description, String type, double amount) {
        this.description = description;
        this.type = type;
        this.amount = amount;
    }

    @Override
    String getType() {
        return "Income";
    }
}
