package model;

import enums.Currency;
import exception.InsufficientFundsException;

public class Account {

    private final long id; // Unique bank account identifier
    private long clientId; // ID for the client who owns the account
    private double balance; // Balance of the accounts

    public Account(long id, long clientId, double balance) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
    }

    public Account(long id, long clientId) {
        this(id, clientId, 0);
    }

    public long getId() {
        return id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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
            amountIsPositive(amount); // Checks if the amount is positive
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
            amountIsPositive(amount); // Check if the amount is positive
            hasSufficientFunds(amount); // Check if the account has sufficient funds to operate
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
            amountIsPositive(amount); // Check if the amount is positive
            hasSufficientFunds(amount); // Check if the account has sufficient funds to operate
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
    public void amountIsPositive(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
    }

    /**
     * Checks if th account has sufficient funds to perform an operation
     * @param amount The amount to be validated
     * @throws InsufficientFundsException Exception thrown if the amount is greater than the account balance
     */
    public void hasSufficientFunds(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("The operation cannot be completed because of insufficient funds.");
        }
    }

    /**
     * Prints a simple message displaying the balance for the account in the console
     */
    public void queryBalance() {
        System.out.println("The balance for this account is: " + this.balance + "€");
    }

    /**
     * Prints the bank account details in the console
     */
    public void printDetails() {
        System.out.println("model.Account ID: " + this.id + ", Client ID: " + this.clientId + ", Balance: " + balance + "€");
    }

}
