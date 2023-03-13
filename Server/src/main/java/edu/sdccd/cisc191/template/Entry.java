package edu.sdccd.cisc191.template;

public abstract class Entry {
    String description;
    String type;
    Double amount;

    abstract String getType();

    String getDescription() {
        return description;
    }
    String getAmount() {
        return ""+amount;
    }
}
