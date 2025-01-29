import enums.Currency;
import model.BusinessAccount;
import model.SavingsAccount;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main class with the menu
 */
public class Main {

    private final static Scanner sc = new Scanner(System.in);

    /**
     * The main method for starting and executing operations in the system
     * @param args
     */
    public static void main(String[] args) {
        boolean systemActive = true; // Flag variable for controlling the loop iterations
        int option = 0;
        String name;
        double balance;

        do {
            printMenu();
            option = inputInt("Enter your option: ");
            switch (option) {
                case 1: // Creating savings account
                    name = inputString("Enter the owner name: ");
                    balance = inputDouble("Enter the balance: ");

                    SavingsAccount savingsAccount = new SavingsAccount(generateRandomId(), name, balance);
                    savingsAccount.printDetails();
                    break;
                case 2: // Creating business account
                    name = inputString("Enter the owner name: ");
                    balance = inputDouble("Enter the balance: ");

                    BusinessAccount businessAccount = new BusinessAccount(generateRandomId(), name, balance);
                    businessAccount.printDetails();
                    break;
                default: // Exit
                    systemActive = false;
            }
        } while (systemActive);

    }

    private static void printMenu() {
        System.out.println("###############");
        System.out.println("+ Bank System +");
        System.out.println("###############");

        System.out.println("\nOptions:");
        System.out.println("1. Create a new savings account");
        System.out.println("2. Create a new business account");
        System.out.println("0. Exit");
    }

    /**
     * Asks the user for an int value
     * @param message The message to be displayed to the user
     * @return The input value
     */
    private static int inputInt(String message) {
        int input = 0;

        boolean correctInput = false;
        do {
            System.out.print(message);
            try {
                input = sc.nextInt();
                sc.nextLine();
                correctInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Option is invalid. Please try again.");
                sc.next(); // Clear the invalid input
            }
        } while (!correctInput);

        return input;
    }

    /**
     * Asks the user for a double value
     * @param message The message to be displayed to the user
     * @return The input value
     */
    private static double inputDouble(String message) {
        double input = 0;

        boolean correctInput = false;
        do {
            System.out.print(message);
            try {
                input = sc.nextDouble();
                sc.nextLine();
                correctInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Option is invalid. Please try again.");
                sc.next(); // Clear the invalid input
            }
        } while (!correctInput);

        return input;
    }

    /**
     * Asks the user for a string value
     * @param message The message to be displayed to the user
     * @return The input value
     */
    private static String inputString(String message) {
        String input = "";

        boolean correctInput = false;
        do {
            System.out.print(message);
            input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Empty values are not allowed. Please try again.");
            } else {
                correctInput = true;
            }
        } while (!correctInput);

        return input;
    }

    /**
     * Generates a random number between 1 and 10.000
     * @return The generated number
     */
    private static long generateRandomId() {
        return (long) (Math.random() * 10000) + 1;
    }

}
