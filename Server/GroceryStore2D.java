package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GroceryStore2D {
    private ArrayList<LinkedList<GroceryItem>> rows;
    private int numRows;
    private int numCols;

    public GroceryStore2D(int initialRows, int initialCols) {
        this.rows = new ArrayList<>(initialRows);
        for (int i = 0; i < initialRows; i++) {
            rows.add(new LinkedList<>());
        }
        this.numRows = initialRows;
        this.numCols = initialCols;
    }

    public GroceryItem getAtIndex(int row, int col) {
        if (isValidIndex(row, col)) {
            LinkedList<GroceryItem> items = rows.get(row);
            if (col >= 0 && col < items.size()) {
                return items.get(col);
            }
        }
        return null;
    }

    public void setAtIndex(int row, int col, GroceryItem item) {
        if (isValidIndex(row, col)) {
            LinkedList<GroceryItem> items = rows.get(row);
            while (items.size() <= col) {
                items.add(null); // Initialize the LinkedList with null elements up to col
            }
            items.set(col, item);
        }
    }

    public int findIndexOf(GroceryItem item) {
        for (int i = 0; i < numRows; i++) {
            LinkedList<GroceryItem> items = rows.get(i);
            int colIndex = items.indexOf(item);
            if (colIndex != -1) {
                return i * numCols + colIndex;
            }
        }
        return -1;
    }

    public void printAll() {
        for (int i = 0; i < numRows; i++) {
            LinkedList<GroceryItem> items = rows.get(i);
            for (int j = 0; j < numCols; j++) {
                if (j < items.size()) {
                    System.out.println("[" + i + "][" + j + "] " + items.get(j));
                }
            }
        }
    }

    public void deleteAtIndex(int row, int col) {
        if (isValidIndex(row, col)) {
            LinkedList<GroceryItem> items = rows.get(row);
            if (col >= 0 && col < items.size()) {
                items.remove(col);
            }
        }
    }

    public void expand(int newRows, int newCols) {
        if (newRows > numRows && newCols > numCols) {
            while (numRows < newRows) {
                rows.add(new LinkedList<>());
                numRows++;
            }
            numCols = newCols;
        }
    }

    public void shrink(int newRows, int newCols) {
        if (newRows >= 0 && newRows < numRows && newCols >= 0 && newCols < numCols) {
            rows.subList(newRows, numRows).clear();
            numRows = newRows;
            numCols = newCols;
        }
    }

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
}
