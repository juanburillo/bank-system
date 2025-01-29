package model;

import enums.Currency;
import exception.InsufficientFundsException;
import interfaces.AccountOperations;

/**
 * Represents a bank account with deposit, withdrawal, and transferring functionality
 */
public abstract class Account implements AccountOperations {

    private final static Currency PREFERRED_CURRENCY = Currency.EUR;

    protected final long id; // Unique bank account identifier
    protected String ownerName; // Name of the owner
    protected double balance; // Balance of the accounts

    public Account(long id, String ownerName, double balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
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
     * Deposits an amount of money into the bank account
     * @param amount The amount of money to be deposited into the account
     */
    public void deposit(double amount) {
        try {
            checkAmountIsPositive(amount); // Checks if the amount is positive
            this.balance += amount;
            System.out.println("Successful deposit of " + amount + PREFERRED_CURRENCY.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Withdraws an amount of money from the bank account
     * @param amount The amount of money to be withdrawn
     */
    public void withdraw(double amount) {
        try {
            checkAmountIsPositive(amount); // Check if the amount is positive
            checkSufficientFunds(amount); // Check if the account has sufficient funds to operate
            this.balance -= amount;
            System.out.println("Successful withdrawal of " + amount + PREFERRED_CURRENCY.toString());
        } catch (IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Transfers a certain amount of money from the account to another one
     * @param amount The amount to be transferred
     * @param account The account to be deposited to
     */
    public void transfer(double amount, Account account) {
        try {
            checkAmountIsPositive(amount); // Check if the amount is positive
            checkSufficientFunds(amount); // Check if the account has sufficient funds to operate
            this.balance -= amount;
            account.deposit(amount);
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
        System.out.println("The balance for this account is: " + this.balance + PREFERRED_CURRENCY.toString());
    }

    /**
     * Prints the bank account details in the console
     */
    public void printDetails() {
        System.out.println("Account ID: " + this.id + ", Owner: " + this.ownerName + ", Balance: " + balance + PREFERRED_CURRENCY.toString());
    }

}
