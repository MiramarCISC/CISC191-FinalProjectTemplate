package edu.sdccd.cisc191.template;

import java.util.Arrays;

public class GroceryStoreSorter {
    public static void quickSort(edu.sdccd.cisc191.GroceryItem[] items, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(items, low, high);
            quickSort(items, low, pivotIndex - 1);
            quickSort(items, pivotIndex + 1, high);
        }
    }

    private static int partition(edu.sdccd.cisc191.GroceryItem[] items, int low, int high) {
        edu.sdccd.cisc191.GroceryItem pivot = items[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Compare items based on their names
            if (items[j].compareTo(pivot) < 0) {
                i++;
                swap(items, i, j);
            }
        }

        // Move the pivot element to its correct position
        swap(items, i + 1, high);

        return i + 1;
    }

    private static <GroceryItem> void swap(GroceryItem[] items, int i, int j) {
        GroceryItem temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}
