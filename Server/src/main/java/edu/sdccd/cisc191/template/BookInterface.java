package edu.sdccd.cisc191.template;

import java.util.ArrayList;

public interface BookInterface {
    public ArrayList<Entry> getEntries();

    public double getTotalExpenses();
    public double getTotalIncome();
    public double getNetProfit();

    public void addEntry(Entry entry);

    public void removeEntry(Entry entry);

}
