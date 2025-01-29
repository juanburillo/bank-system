package model;

import enums.Currency;
import exception.InsufficientFundsException;
import interfaces.AccountOperations;

/**
 * Represents a bank account with deposit, withdrawal, and transferring functionality
 */
public abstract class Account implements AccountOperations {

    protected final long id; // Unique bank account identifier
    protected String ownerName; // Name of the owner
    protected double balance; // Balance of the accounts

    public Account(long id, String ownerName, double balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public Account(long id, String ownerName) {
        this(id, ownerName, 0);
    }

    public long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Deposits an amount of money of the specified currency into the bank account
     * @param amount The amount of money to be deposited into the account
     * @param currency The currency
     */
    public void deposit(double amount, Currency currency) {
        try {
            checkAmountIsPositive(amount); // Checks if the amount is positive
            this.balance += amount;
            System.out.println("Successful deposit of " + amount + currency.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Withdraws an amount of money of the specified currency from the bank account
     * @param amount The amount of money to be withdrawn
     * @param currency The currency
     */
    public void withdraw(double amount, Currency currency) {
        try {
            checkAmountIsPositive(amount); // Check if the amount is positive
            checkSufficientFunds(amount); // Check if the account has sufficient funds to operate
            this.balance -= amount;
            System.out.println("Successful withdrawal of " + amount + currency.toString());
        } catch (IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Transfers a certain amount of money from the account to another one
     * @param amount The amount to be transferred
     * @param currency The currency
     * @param account The account to be deposited to
     */
    public void transfer(double amount, Currency currency, Account account) {
        try {
            checkAmountIsPositive(amount); // Check if the amount is positive
            checkSufficientFunds(amount); // Check if the account has sufficient funds to operate
            this.balance -= amount;
            account.deposit(amount, currency);
        } catch (IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the amount is positive and different from 0
     * @param amount The amount to be validated
     * @throws IllegalArgumentException Exception thrown if the amount is negative or 0
     */
    private void checkAmountIsPositive(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
    }

    /**
     * Checks if th account has sufficient funds to perform an operation
     * @param amount The amount to be validated
     * @throws InsufficientFundsException Exception thrown if the amount is greater than the account balance
     */
    private void checkSufficientFunds(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("The operation cannot be completed because of insufficient funds.");
        }
    }

    /**
     * Prints a simple message displaying the balance for the account in the console
     */
    public void printBalance() {
        System.out.println("The balance for this account is: " + this.balance + "€");
    }

    /**
     * Prints the bank account details in the console
     */
    public void printDetails() {
        System.out.println("model.Account ID: " + this.id + ", Owner: " + this.ownerName + ", Balance: " + balance + "€");
    }

}
