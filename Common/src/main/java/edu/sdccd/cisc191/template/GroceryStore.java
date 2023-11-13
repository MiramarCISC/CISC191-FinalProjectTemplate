package edu.sdccd.cisc191;

import java.util.Arrays;
import java.util.Scanner;

public class GroceryStore {
    public GroceryItem[] items;
    public int size;

    public GroceryStore(int initialSize) {
        this.items = new GroceryItem[initialSize];
        this.size = 0;
    }
    public void sortItems() {
        GroceryStoreSorter.quickSort(items, 0, size - 1); // Provide the array and the range to sort
    }

    public int findItem(GroceryItem item) {
        return GroceryStoreSearcher.binarySearch(items, size, item);
    }

    public void setAtIndex(int index, GroceryItem item) {
        if (index >= 0 && index < items.length) {
            items[index] = item;
        }
    }

    public GroceryItem getAtIndex(int index) {
        if (index >= 0 && index < size) {
            return items[index];
        }
        return null; // Return null if the index is out of bounds
    }


    public int findIndexOf(GroceryItem item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(items[i]);
        }
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                items[i] = items[i + 1];
            }
            items[size - 1] = null; // Remove the last item
            size--;
        }
    }

    public void expand(int newSize) {
        items = Arrays.copyOf(items, newSize);
    }

    public void shrink(int newSize) {
        if (newSize >= 0 && newSize < size) {
            items = Arrays.copyOf(items, newSize);
            size = newSize;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GroceryStore store = new GroceryStore(10); // Initial size of 10


        GroceryStore2D store2D = new GroceryStore2D(5, 10); // Initial size of 5x10

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMenu:");
            System.out.println("1. GroceryStore (1D Array) Operations");
            System.out.println("2. GroceryStore2D (2D Array) Operations");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    groceryStoreMenu(store, scanner);
                    break;

                case 2:
                    groceryStore2DMenu(store2D, scanner);
                    break;

                case 3:
                    isRunning = false;
                    break;
            }
        }

        scanner.close();
    }

    private static void groceryStoreMenu(GroceryStore store, Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nGroceryStore Menu:");
            System.out.println("1. Get item at index");
            System.out.println("2. Set item at index");
            System.out.println("3. Find index of item");
            System.out.println("4. Print all items");
            System.out.println("5. Delete item at index");
            System.out.println("6. Expand array");
            System.out.println("7. Shrink array");
            System.out.println("8. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter index: ");
                    int getIndex = scanner.nextInt();
                    System.out.println(store.getAtIndex(getIndex));
                    break;

                case 2:
                    System.out.print("Enter index: ");
                    int setIndex = scanner.nextInt();
                    System.out.print("Enter item name: ");
                    String itemName = scanner.next();
                    System.out.print("Enter item price:");
                    double itemPrice = scanner.nextDouble();
                    store.setAtIndex(setIndex, new GroceryItem(itemName, itemPrice));
                    break;

                case 3:
                    System.out.print("Enter item name to find: ");
                    String findItemName = scanner.next();
                    System.out.print("Enter item price to find: ");
                    double findItemPrice = scanner.nextDouble();
                    GroceryItem itemToFind = new GroceryItem(findItemName,findItemPrice);
                    int foundIndex = store.findIndexOf(itemToFind);
                    if (foundIndex >= 0) {
                        System.out.println("Item found at index: " + foundIndex);
                    } else {
                        System.out.println("Item not found in the store.");
                    }
                    break;

                case 4:
                    store.printAll();
                    break;

                case 5:
                    System.out.print("Enter index to delete: ");
                    int deleteIndex = scanner.nextInt();
                    store.deleteAtIndex(deleteIndex);
                    break;

                case 6:
                    System.out.print("Enter new size to expand: ");
                    int expandSize = scanner.nextInt();
                    store.expand(expandSize);
                    break;

                case 7:
                    System.out.print("Enter new size to shrink: ");
                    int shrinkSize = scanner.nextInt();
                    store.shrink(shrinkSize);
                    break;

                case 8:
                    isRunning = false;
                    break;
            }
        }
    }

    private static void groceryStore2DMenu(GroceryStore2D store2D, Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nGroceryStore2D Menu:");
            System.out.println("1. Get item at index");
            System.out.println("2. Set item at index");
            System.out.println("3. Find index of item");
            System.out.println("4. Print all items");
            System.out.println("5. Delete item at index");
            System.out.println("6. Expand array");
            System.out.println("7. Shrink array");
            System.out.println("8. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter row index: ");
                    int rowIndex = scanner.nextInt();
                    System.out.print("Enter column index: ");
                    int colIndex = scanner.nextInt();
                    System.out.println(store2D.getAtIndex(rowIndex, colIndex));
                    break;

                case 2:
                    System.out.print("Enter row index: ");
                    int rowIndex2 = scanner.nextInt();
                    System.out.print("Enter column index: ");
                    int colIndex2 = scanner.nextInt();
                    System.out.print("Enter item name: ");
                    String itemName = scanner.next();
                    System.out.print("Enter item price: ");
                    double itemPrice = scanner.nextDouble();
                    store2D.setAtIndex(rowIndex2, colIndex2, new GroceryItem(itemName, itemPrice));
                    break;

                case 3:
                    System.out.print("Enter item name to find: ");
                    String findItemName = scanner.next();
                    System.out.print("Enter item price to find: ");
                    double findItemPrice = scanner.nextDouble();
                    GroceryItem itemToFind = new GroceryItem(findItemName, findItemPrice);
                    int foundIndex = store2D.findIndexOf(itemToFind);
                    if (foundIndex != -1) {
                        int row = foundIndex / store2D.numCols;
                        int col = foundIndex % store2D.numCols;
                        System.out.println("Item found at row: " + row + " and column: " + col);
                    } else {
                        System.out.println("Item not found in the store.");
                    }
                    break;


                case 4:
                    store2D.printAll();
                    break;

                case 5:
                    System.out.print("Enter row index to delete: ");
                    int rowIndex3 = scanner.nextInt();
                    System.out.print("Enter column index to delete: ");
                    int colIndex3 = scanner.nextInt();
                    store2D.deleteAtIndex(rowIndex3, colIndex3);
                    break;

                case 6:
                    System.out.print("Enter new row size to expand: ");
                    int expandRowSize = scanner.nextInt();
                    System.out.print("Enter new column size to expand: ");
                    int expandColSize = scanner.nextInt();
                    store2D.expand(expandRowSize, expandColSize);
                    break;

                case 7:
                    System.out.print("Enter new row size to shrink: ");
                    int shrinkRowSize = scanner.nextInt();
                    System.out.print("Enter new column size to shrink: ");
                    int shrinkColSize = scanner.nextInt();
                    store2D.shrink(shrinkRowSize, shrinkColSize);
                    break;

                case 8:
                    isRunning = false;
                    break;
            }
        }
    }
}



