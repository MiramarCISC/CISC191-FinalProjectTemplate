package org.example;
import java.util.Arrays;
import java.util.ArrayList;

public class GroceryStore2D {
    private GroceryItem[][] items;
    private int numRows;
    private int numCols;

    public GroceryStore2D(int initialRows, int initialCols) {
        this.items = new GroceryItem[initialRows][initialCols];
        this.numRows = initialRows;
        this.numCols = initialCols;
    }

    public GroceryItem getAtIndex(int row, int col) {
        if (isValidIndex(row, col)) {
            return items[row][col];
        }
        return null;
    }

    public void setAtIndex(int row, int col, GroceryItem item) {
        if (isValidIndex(row, col)) {
            items[row][col] = item;
        }
    }

    public int findIndexOf(GroceryItem item) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (items[i][j] != null && items[i][j].equals(item)) {
                    return i * numCols + j;
                }
            }
        }
        return -1;
    }

    public void printAll() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (items[i][j] != null) {
                    System.out.println("[" + i + "][" + j + "] " + items[i][j]);
                }
            }
        }
    }

    public void deleteAtIndex(int row, int col) {
        if (isValidIndex(row, col)) {
            items[row][col] = null;
        }
    }

    public void expand(int newRows, int newCols) {
        if (newRows > numRows && newCols > numCols) {
            items = Arrays.copyOf(items, newRows);
            for (int i = numRows; i < newRows; i++) {
                items[i] = new GroceryItem[newCols];
            }
            numRows = newRows;
            numCols = newCols;
        }
    }

    public void shrink(int newRows, int newCols) {
        if (newRows >= 0 && newRows < numRows && newCols >= 0 && newCols < numCols) {
            items = Arrays.copyOf(items, newRows);
            for (int i = 0; i < newRows; i++) {
                items[i] = Arrays.copyOf(items[i], newCols);
            }
            numRows = newRows;
            numCols = newCols;
        }
    }

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }


}
