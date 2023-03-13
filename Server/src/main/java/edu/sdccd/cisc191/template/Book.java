package edu.sdccd.cisc191.template;

import java.util.ArrayList;

public class Book implements BookInterface {
    private ArrayList<Entry> entries = new ArrayList<>();

    public Book() {

    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }
    public ArrayList<Entry> getExpenses() {
        ArrayList<Entry> expenses = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).type.equals("Expense")) {
                expenses.add(entries.get(i));
            }
        }
        return expenses;
    }
    public ArrayList<Entry> getIncome() {
        ArrayList<Entry> incomes = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).type.equals("Income")) {
                incomes.add(entries.get(i));
            }
        }
        return incomes;
    }

    @Override
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    @Override
    public void removeEntry(Entry entry) {
        entries.remove(entries.indexOf(entry));
    }

    public double getTotalExpenses() {
        double total = 0.0;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).type.equals("Expense") ) {
                total += entries.get(i).amount;
            }
        }
        return total;
    }
    public double getTotalIncome() {
        double total = 0.0;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).type.equals("Income") ) {
                total += entries.get(i).amount;
            }
        }
        return total;
    }
    public double getNetProfit() {
        double net = 0.0;
        double totalIncome = getTotalIncome();
        double totalExpenses = getTotalExpenses();
        net = totalIncome - totalExpenses;
        return net;
    }
}
