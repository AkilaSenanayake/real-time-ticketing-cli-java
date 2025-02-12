package iit.oop.cw;

import iit.oop.cw.configurations.ConfigProcess;
import iit.oop.cw.configurations.Configuration;
import iit.oop.cw.tickets.TicketPool;
import iit.oop.cw.users.Customer;
import iit.oop.cw.users.Vendor;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class TicketSystem {
    private static volatile boolean isRunning = false; // Use volatile for thread safety
    private static volatile boolean showMenu = true; // Control menu visibility
    private static final List<Thread> vendorThreads = new CopyOnWriteArrayList<>();
    private static final List<Thread> customerThreads = new CopyOnWriteArrayList<>();
    private static Configuration configFile;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        while (!end) {
            if (showMenu) {
                System.out.println("-- Real-time Ticketing System --");
                System.out.println("1. New Configuration");
                System.out.println("2. Load Configuration");
                System.out.println("3. Display Configuration");
                System.out.println("4. Start");
                System.out.println("5. Stop");
                System.out.println("6. Exit\n");
                System.out.print("Select your choice: ");
            }

            int choice = scanner.nextInt();

            if (!showMenu) {
                System.out.println();
            }

            switch (choice) {
                case 1:
                    newConfig();
                    break;
                case 2:
                    loadConfig();
                    break;
                case 3:
                    displayConfig();
                    break;
                case 4:
                    startSimulate();
                    break;
                case 5:
                    stopSimulate();
                    break;
                case 6:
                    end = true;
                    System.out.println("Exiting the system...");
                    stopSimulate();
                    break;
                default:
                    System.out.println("Invalid selection. Try again!");
            }
        }
        scanner.close();
    }

    private static void newConfig() {
        if (configFile == null) {
            configFile = new Configuration(0, 0, 0);
        }
        configFile.enterUserConfiguration();
        ConfigProcess.saveConfigToFile(configFile, "configFile.json");
        System.out.println("Successfully configured!");
    }

    public static void loadConfig() {
        configFile = ConfigProcess.loadConfigFromFile("configFile.json");
        if (configFile != null) {
            System.out.println("Successfully loaded!");
        } else {
            System.out.println("Failed to load! The system is not configured.");
        }
    }

    private static void displayConfig() {
        if (configFile == null) {
            System.out.println("No configuration available!\n");
            return;
        }
        System.out.println("- System Configuration -");
        System.out.println("Max Ticket Capacity: " + configFile.getMaxTicketCapacity());
        System.out.println("Total Tickets: " + configFile.getTotalTickets());
        System.out.println("Customer Retrieval Rate: " + configFile.getCustomerRetrievalRate());
        System.out.println("Ticket Release Rate: " + configFile.getTicketReleaseRate());
        System.out.println();
    }

    private static void startSimulate() {
        if (configFile == null) {
            System.out.println("Please configure the system before starting!\n");
            return;
        }
        if (isRunning) {
            System.out.println("Simulation is already running!\n");
            return;
        }

        System.out.println("Starting simulation...");
        isRunning = true;
        showMenu = false; // Hide menu during the simulation

        TicketPool ticketPool = new TicketPool(configFile);

        // Vendor threads
        //3 vendors added
        Thread vendor1 = new Thread(new Vendor(ticketPool, configFile.getTicketReleaseRate(), 1));
        Thread vendor2 = new Thread(new Vendor(ticketPool, configFile.getTicketReleaseRate(), 2));
        Thread vendor3 = new Thread(new Vendor(ticketPool, configFile.getTicketReleaseRate(), 3));

        vendorThreads.add(vendor1);
        vendorThreads.add(vendor2);
        vendorThreads.add(vendor3);

        vendor1.start();  //start vendor threads
        vendor2.start();
        vendor3.start();

        // Customer threads
        // 2 customers added
        Thread customer1 = new Thread(new Customer(ticketPool, configFile.getCustomerRetrievalRate(), 1));
        Thread customer2 = new Thread(new Customer(ticketPool, configFile.getCustomerRetrievalRate(), 2));

        customerThreads.add(customer1);
        customerThreads.add(customer2);

        customer1.start();  //start customer threads
        customer2.start();

        System.out.println("Simulation started.\n");
    }

    private static void stopSimulate() {
        if (!isRunning) {
            System.out.println("Simulation is not running!\n");
            return;
        }

        System.out.println("Stopping simulation...");
        isRunning = false;

        // Interrupt all vendor threads
        for (Thread thread : vendorThreads) {
            thread.interrupt();
        }

        // Interrupt all customer threads
        for (Thread thread : customerThreads) {
            thread.interrupt();
        }

        // Clear thread lists
        vendorThreads.clear();
        customerThreads.clear();

        System.out.println("Simulation stopped.\n");

        // Display closing messages before showing menu
        for (Thread thread : vendorThreads) {
            System.out.println("Vendor thread closed.");
        }
        for (Thread thread : customerThreads) {
            System.out.println("Customer thread closed.");
        }

        showMenu = true;
    }
}
