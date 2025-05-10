package week01.aditiyawan.id.ac.umn;

import week01.aditiyawan.id.ac.umn.products.Item;
import week01.aditiyawan.id.ac.umn.products.Merchandise;
import week01.aditiyawan.id.ac.umn.products.Recording;
import week01.aditiyawan.id.ac.umn.products.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Item> items = new ArrayList<>();
    private static final List<Invoice> invoices = new ArrayList<>();

    public static void seedItems() {
        Integer id = 1;

        items.add(new Ticket(id++, "Concert A", 150.0, 50, "Music", LocalDate.of(2025, 12, 31)));
        items.add(new Ticket(id++, "Theater B", 100.0, 30, "Drama", LocalDate.of(2025, 11, 15)));
        items.add(new Ticket(id++, "Festival C", 200.0, 20, "Festival", LocalDate.of(2025, 10, 10)));

        items.add(new Merchandise(id++, "Band T-Shirt", 25.0, 100, "Fashion", "Baju"));
        items.add(new Merchandise(id++, "Movie Poster", 10.0, 200, "Poster", "Poster"));
        items.add(new Merchandise(id++, "Keychain", 5.0, 150, "Accessories", "Gantungan Kunci"));

        items.add(new Recording(id++, "Live Concert 2024", 50.0, 40, "Live", 120));
        items.add(new Recording(id++, "Standup Special", 40.0, 25, "Comedy", 90));
        items.add(new Recording(id++, "Drama Series Ep.1", 20.0, 60, "Drama", 45));
        items.add(new Recording(id++, "Podcast Session", 15.0, 70, "Podcast", 60));
    }

    public static void saveItemsToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Item item : items) {
                writer.write(item.printDetails() + "\n\n");
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }

    public static void saveInvoicesToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Invoice invoice : invoices) {
                writer.write(invoice.printDetails() + "\n\n");
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }

    public static void printMenu() {
        System.out.println("=== Cashier Program ===");
        System.out.println("1. List of items");
        System.out.println("2. Add item");
        System.out.println("3. Edit item");
        System.out.println("4. Delete item");
        System.out.println("5. Search item");
        System.out.println("6. Buy items");
        System.out.println("7. Show purchased items invoices");
        System.out.println("0. Exit");
    }

    public static void printListOfItem() {
        if (items.isEmpty()) {
            System.out.println("There are no items");
            return;
        }

        for (Item item : items) {
            System.out.println(item.printDetails());
            System.out.println("----------------------------------------");
        }
    }

    public static void printListOfItem(String category) {
        if (items.isEmpty()) {
            System.out.println("There are no items");
            return;
        }

        for (Item item : items) {
            if (item.getCategory().equals(category)) {
                System.out.println(item.printDetails());
                System.out.println("----------------------------------------");
            }
        }
    }

    public static Item getItemById(Integer id) {
        for (Item item : items) {
            if (item.getId() != null && Objects.equals(item.getId(), id)) {
                return item;
            }
        }
        return null;
    }

    public static void addItemMenu() {
        System.out.println("=== Add Item ===");
        System.out.println("1. Ticket");
        System.out.println("2. Merchandise");
        System.out.println("3. Recording");
    }

    public static void addItem() {
        try {
            addItemMenu();

            System.out.print("Choose type of item: ");
            Integer choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            Double price = scanner.nextDouble();

            System.out.print("Stock: ");
            Integer stock = scanner.nextInt();

            switch (choice) {
                case 1:
                    items.add(new Ticket(items.size() + 1, name, price, stock, "Ticket", LocalDate.now()));
                    break;

                case 2:
                    System.out.print("Type of merch (shirt / post): ");
                    String type = scanner.nextLine();

                    items.add(new Merchandise(items.size() + 1, name, price, stock, "Merchandise", type));
                    break;

                case 3:
                    System.out.print("Recording duration (minutes): ");
                    Integer duration = scanner.nextInt();

                    items.add(new Recording(items.size() + 1, name, price, stock, "Recording", duration));
            }

            saveItemsToFile("Items.txt");

            System.out.println("Item added successfully");
        } catch (Exception e) {
            System.err.println("Error adding item: " + e.getMessage());
        }
    }

    public static void editItemMenu() {
        System.out.println("=== Edit Item ===");
        System.out.println("1. Ticket");
        System.out.println("2. Merchandise");
        System.out.println("3. Recording");
    }

    public static void editItem() {
        try {
            editItemMenu();

            System.out.print("Choose type of item: ");
            Integer choice = scanner.nextInt();
            scanner.nextLine();

            String category;
            switch (choice) {
                case 1:
                     category = "Ticket";
                    break;
                case 2:
                    category = "Merchandise";
                    break;
                case 3:
                    category = "Recording";
                    break;
                default:
                    System.out.println("Invalid category");
                    return;
            }

            printListOfItem(category);

            System.out.print("Enter item's ID: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            Item chosenItem = getItemById(id);

            if (chosenItem == null || !chosenItem.getId().equals(id)) {
                System.out.println("Item not found");
                return;
            }

            System.out.print("New name [" + chosenItem.getName() + "]: ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) chosenItem.setName(newName);

            System.out.print("New price [" + chosenItem.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            if (!priceInput.isEmpty()) chosenItem.setPrice(Double.parseDouble(priceInput));

            System.out.print("New stock [" + chosenItem.getStock() + "]: ");
            String stockInput = scanner.nextLine();
            if (!stockInput.isEmpty()) chosenItem.setStock(Integer.parseInt(stockInput));

            switch (chosenItem) {
                case Merchandise merch:
                    System.out.print("New type [" + merch.getType() + "]: ");
                    String type = scanner.nextLine();
                    if (!type.isEmpty()) merch.setType(type);
                    break;

                case Recording recording:
                    System.out.print("New duration [" + recording.getDurationInMinutes() + "]: ");
                    String duration = scanner.nextLine();
                    if (!duration.isEmpty()) recording.setDurationInMinutes(Integer.parseInt(duration));
                    break;

                case Ticket ticket:
                    System.out.print("New date (YYYY-MM-DD) [" + ticket.getValidUntil() + "]: ");
                    String date = scanner.nextLine();
                    if (!date.isEmpty()) ticket.setValidUntil(LocalDate.parse(date));
                    break;

                default:
                    System.out.println("Failed to edit item");
                    return;
            }

            saveItemsToFile("Items.txt");
            System.out.println("Item updated successfully");
        } catch (Exception e) {
            System.err.println("Error editing item: " + e.getMessage());
        }
    }

    public static void deleteItem() {
        try {
            printListOfItem();

            System.out.print("Choose item by ID: ");
            Integer id = scanner.nextInt();

            Item chosenItem = getItemById(id);

            if (chosenItem == null || !chosenItem.getId().equals(id)) {
                System.out.println("Item not found");
                return;
            }

            items.remove(chosenItem);
            saveItemsToFile("Items.txt");

            System.out.println("Item deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting item: " + e.getMessage());
        }
    }

    public static List<Item> searchItemByName(String name) {
        String lowerName = name.toLowerCase();
        List<Item> results = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().toLowerCase().contains(lowerName)) {
                results.add(item);
            }
        }

        return results;
    }

    public static void searchItems() {
        System.out.print("Enter item's name: ");
        String name = scanner.nextLine();

        List<Item> results = searchItemByName(name);

        if (results.isEmpty()) {
            System.out.println("Item not found");
            return;
        }

        for (Item item : results) {
            System.out.println(item.printDetails());
            System.out.println("----------------------------------------");
        }
    }

    public static void buyItems() {
        try {
            printListOfItem();

            System.out.print("Enter item's ID: ");
            Integer id = scanner.nextInt();

            Item item = getItemById(id);

            if (item == null || !item.getId().equals(id)) {
                System.out.println("Item not found");
                return;
            }

            System.out.print("Quantity: ");
            Integer quantity = scanner.nextInt();
            scanner.nextLine();

            if (item.getStock() < quantity) {
                System.out.println("Insufficient stock");
                return;
            }

            item.minusStock(quantity);
            invoices.add(new Invoice(item, quantity, item.getPrice() * quantity, LocalDate.now()));

            saveInvoicesToFile("Invoices.txt");

            System.out.println("Item bought successfully");
        } catch (Exception e) {
            System.err.println("Error purchasing items: " + e.getMessage());
        }
    }

    public static void printInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices found");
            return;
        }

        for (Invoice invoice : invoices) {
            System.out.println(invoice.printDetails());
            System.out.println("----------------------------------------");
        }
    }

    public static void main(String[] args) {
        Integer choice;
        seedItems();
        saveItemsToFile("Items.txt");

        while (true) {
            try {
                printMenu();
                System.out.print("Choose: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        printListOfItem();
                        break;

                    case 2:
                        addItem();
                        break;

                    case 3:
                        editItem();
                        break;

                    case 4:
                        deleteItem();
                        break;

                    case 5:
                        searchItems();
                        break;

                    case 6:
                        buyItems();
                        break;

                    case 7:
                        printInvoices();
                        break;

                    case 0:
                        System.out.println("Exiting the program...");
                        return;

                    default:
                        throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

}