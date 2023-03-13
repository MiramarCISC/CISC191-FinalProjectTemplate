package edu.sdccd.cisc191.template;

public class Expense extends Entry {

    public Expense (String description, String type, double amount) {
        this.description = description;
        this.type = type;
        this.amount = amount;
    }
    @Override
    String getType() {
        return "Expense";
    }
}
