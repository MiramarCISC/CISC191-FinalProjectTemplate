package edu.sdccd.cisc191.template;

import java.io.*;
import java.util.Scanner;

public class Server implements Serializable {
    private Inventory inventory;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        inventory = loadInventoryFromFile();
        if (inventory == null) {
            inventory = new Inventory();
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCar(scanner);
                    break;
                case 2:
                    getCar(scanner);
                    break;
                case 3:
                    printAllCars();
                    break;
                case 4:
                    removeCar(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    private void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add a car");
        System.out.println("2. Get a car");
        System.out.println("3. Print all cars");
        System.out.println("4. Remove a car");
        System.out.println("5. Exit");
        System.out.println("Enter your choice:");
    }

    private void addCar(Scanner scanner) {
        System.out.println("Enter the make:");
        String make = scanner.nextLine();
        System.out.println("Enter the model:");
        String model = scanner.nextLine();
        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter the color:");
        String color = scanner.nextLine();
        System.out.println("Enter the price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        Car car = new Car(make, model, year, color, price);
        inventory.addCar(car);
        System.out.println("Car added to inventory.");
    }

    private void getCar(Scanner scanner) {
        System.out.println("Enter the make:");
        String make = scanner.nextLine();
        System.out.println("Enter the model:");
        String model = scanner.nextLine();

        Car car = inventory.getCar(make, model);
        if (car != null) {
            System.out.println(car);
        } else {
            System.out.println("Car not found in inventory.");
        }
    }

    private void printAllCars() {
        System.out.println("All cars in inventory:");
        for (Car car : inventory.getAllCars()) {
            System.out.println(car);
        }
    }

    private void removeCar(Scanner scanner) {
        System.out.println("Enter the make:");
        String make = scanner.nextLine();
        System.out.println("Enter the model:");
        String model = scanner.nextLine();

        Car car = inventory.getCar(make, model);
        if (car != null) {
            inventory.removeCar(make, model);
            System.out.println("Car removed from inventory.");
        } else {
            System.out.println("Car not found in inventory.");
        }
    }

    private Inventory loadInventoryFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("inventory.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Inventory) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveInventoryToFile(Inventory inventory) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("inventory.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        saveInventoryToFile(inventory);
    }
}

