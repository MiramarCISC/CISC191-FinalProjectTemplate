package edu.sdccd.cisc191;

public class GroceryStoreSearcher {
    public static int binarySearch(GroceryItem[] items, int size, GroceryItem item) {
        return binarySearch(items, item, 0, size - 1);
    }

    private static int binarySearch(GroceryItem[] items, GroceryItem item, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if the middle item is a match
            if (items[mid].equals(item)) {
                return mid;
            }

            // If the item is in the lower half, search there
            if (items[mid].compareTo(item) > 0) {
                high = mid - 1;
            }
            // If the item is in the upper half, search there
            else {
                low = mid + 1;
            }
        }

        // If the item is not found, return -1
        return -1;
    }
}
