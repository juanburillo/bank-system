import model.Account;
import model.BusinessAccount;
import model.SavingsAccount;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
        // Lists for storing new accounts
        ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
        ArrayList<BusinessAccount> businessAccounts = new ArrayList<>();

        // Flag variable for controlling the loop iterations
        boolean systemActive = true;

        // Menu loop
        do {
            printMenu(); // Prints the menu
            int option = inputInt("Enter your option: ");

            switch (option) {
                case 1 -> createSavingsAccount(savingsAccounts);
                case 2 -> createBusinessAccount(businessAccounts);
                case 3 -> listAccounts(savingsAccounts, businessAccounts);
                case 4 -> makeDeposit(savingsAccounts, businessAccounts);
                case 5 -> makeWithdrawal(savingsAccounts, businessAccounts);
                case 6 -> makeTransfer(savingsAccounts, businessAccounts);
                case 7 -> applyInterest(savingsAccounts);
                case 8 -> queryBusinessAccountTransferHistory(businessAccounts);
                case 9 -> systemActive = false;
                default -> System.out.println("Invalid option. Please try again with a number from 1 to 9.");
            }
        } while (systemActive);
    }

    /**
     * Prints the options menu
     */
    private static void printMenu() {
        System.out.println("###############\n+ Bank System +\n###############");
        System.out.println("\nOptions:");
        System.out.println("1. Create a new savings account");
        System.out.println("2. Create a new business account");
        System.out.println("3. Display account details");
        System.out.println("4. Make a deposit");
        System.out.println("5. Make a withdrawal");
        System.out.println("6. Make a transfer");
        System.out.println("7. Apply interest to savings account");
        System.out.println("8. Query business account transfer history");
        System.out.println("9. Exit");
    }

    /**
     * Creates a new savings account
     * @param savingsAccounts The savings accounts
     */
    private static void createSavingsAccount(List<SavingsAccount> savingsAccounts) {
        // Asks for user input
        String name = inputString("Enter the owner name: ");
        double balance = inputDouble("Enter the balance: ");

        // Creates the account and saves it in the collection
        SavingsAccount account = new SavingsAccount(generateRandomId(), name, balance);
        savingsAccounts.add(account);

        account.printDetails(); // Prints the account details
    }

    /**
     * Creates a new business account
     * @param businessAccounts The business account list
     */
    private static void createBusinessAccount(List<BusinessAccount> businessAccounts) {
        // Asks for user input
        String name = inputString("Enter the owner name: ");
        double balance = inputDouble("Enter the balance: ");

        // Creates the account and saves it in the collection
        BusinessAccount account = new BusinessAccount(generateRandomId(), name, balance);
        businessAccounts.add(account);

        account.printDetails(); // Prints the account details
    }

    /**
     * Lists all the accounts in the system
     * @param savingsAccounts The saving accounts
     * @param businessAccounts The business accounts
     */
    private static void listAccounts(List<SavingsAccount> savingsAccounts, List<BusinessAccount> businessAccounts) {
        for (SavingsAccount account : savingsAccounts) {
            account.printDetails();
        }
       for (BusinessAccount account : businessAccounts) {
           account.printDetails();
       }
    }

    /**
     * Makes a deposit into a specified account
     * @param savingsAccounts The saving accounts
     * @param businessAccounts The business accounts
     */
    private static void makeDeposit(List<SavingsAccount> savingsAccounts, List<BusinessAccount> businessAccounts) {
        Account account = selectAccountForOperation(savingsAccounts, businessAccounts);
        if (account != null) {
            double amount = inputDouble("Enter the deposit amount: ");
            account.deposit(amount);
        } else {
            System.out.println("No account with the specified ID exists.");
        }
    }

    /**
     * Performs a withdrawal from a specified account
     * @param savingsAccounts The saving accounts
     * @param businessAccounts The business accounts
     */
    private static void makeWithdrawal(List<SavingsAccount> savingsAccounts, List<BusinessAccount> businessAccounts) {
        Account account = selectAccountForOperation(savingsAccounts, businessAccounts);
        if (account != null) {
            double amount = inputDouble("Enter the withdrawal amount: ");
            account.withdraw(amount);
        } else {
            System.out.println("No account with the specified ID exists.");
        }
    }

    /**
     * Performs a transfer from one account to another
     * @param savingsAccounts The saving accounts
     * @param businessAccounts The business accounts
     */
    private static void makeTransfer(List<SavingsAccount> savingsAccounts, List<BusinessAccount> businessAccounts) {
        Account fromAccount = selectAccountForOperation(savingsAccounts, businessAccounts);
        if (fromAccount != null) {
            Account toAccount = selectAccountForOperation(savingsAccounts, businessAccounts);
            if (toAccount != null) {
                double amount = inputDouble("Enter the transfer amount: ");
                fromAccount.transfer(amount, toAccount);
            } else {
                System.out.println("No account with the specified ID exists.");
            }
        } else {
            System.out.println("No account with the specified ID exists.");
        }
    }

    /**
     * Applies interest to a specified savings account
     * @param savingsAccounts The savings accounts
     */
    private static void applyInterest(List<SavingsAccount> savingsAccounts) {
        SavingsAccount account = selectSavingsAccountForOperation(savingsAccounts);
        if (account != null) account.applyInterest(); else System.out.println("No account with the specified ID exists.");
    }

    /**
     * Queries the transfer history of a business account
     * @param businessAccounts The business accounts
     */
    private static void queryBusinessAccountTransferHistory(List<BusinessAccount> businessAccounts) {
        BusinessAccount account = selectBusinessAccountForOperation(businessAccounts);
        if (account != null) account.printTransferHistory(); else System.out.println("No account with the specified ID exists.");
    }

    /**
     * Selects an account for an operation
     * @param savingsAccounts The savings accounts
     * @param businessAccounts The business accounts
     * @return The selected account
     */
    private static Account selectAccountForOperation(List<SavingsAccount> savingsAccounts, List<BusinessAccount> businessAccounts) {
        listAccounts(savingsAccounts, businessAccounts);
        int accountId = inputInt("Enter the account ID: ");
        for (SavingsAccount account : savingsAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        for (BusinessAccount account : businessAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }

    /**
     * Selects a savings account for an operation
     * @param savingsAccounts The savings accounts
     * @return The selected account
     */
    private static SavingsAccount selectSavingsAccountForOperation(List<SavingsAccount> savingsAccounts) {
        listAccounts(savingsAccounts, new ArrayList<>());
        int accountId = inputInt("Enter the account ID: ");
        for (SavingsAccount account : savingsAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }

    /**
     * Selects a business account for an operation
     * @param businessAccounts The business accounts
     * @return The selected account
     */
    private static BusinessAccount selectBusinessAccountForOperation(List<BusinessAccount> businessAccounts) {
        listAccounts(new ArrayList<>(), businessAccounts);
        int accountId = inputInt("Enter the account ID: ");
        for (BusinessAccount account : businessAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
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
