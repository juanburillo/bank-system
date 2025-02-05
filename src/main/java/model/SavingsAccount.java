package model;

import enums.Currency;
import exception.TransferLimitExceededException;

/**
 * Represents a savings account. Inherits the Account abstract class, and has a different field:
 * interestRate. A transfer limit is set.
 */
public class SavingsAccount extends Account {

    private static final Currency PREFERRED_CURRENCY = Currency.EUR;

    private static final double TRANSFER_LIMIT = 500; // Transfer limit set to personal saving accounts

    private double interestRate; // The interest rate expressed in decimal form (2% would be 0.02)

    public SavingsAccount(long id, String ownerName, double balance, double interestRate) {
        super(id, ownerName, balance);
        this.interestRate = interestRate;
    }

    public SavingsAccount(long id, String ownerName, double balance) {
        super(id, ownerName, balance);
        this.interestRate = 0.02;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Transfers a certain amount of money from the account to another one if it does not exceed
     * the transfer limit
     * @param amount The amount to be transferred
     * @param account The account to be deposited to
     */
    @Override
    public void transfer(double amount, Account account) {
        try {
            checkTransferLimit(amount);
            super.transfer(amount, account);
        } catch (TransferLimitExceededException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Applies interest to the account balance based on the current interest rate
     */
    public void applyInterest() {
        double interest = this.balance * this.interestRate;
        this.balance += interest;
        System.out.println("Interest of " + interest + PREFERRED_CURRENCY.toString() + " applied. New balance: " + this.balance + PREFERRED_CURRENCY.toString());
    }

    /**
     * Checks if an amount surpasses the transfer limit
     * @param amount The amount to be validated
     * @throws TransferLimitExceededException Exception thrown if the amount exceeds the transfer limit
     */
    private void checkTransferLimit(double amount) throws TransferLimitExceededException {
        if (amount > TRANSFER_LIMIT) {
            double excess = amount - TRANSFER_LIMIT;
            throw new TransferLimitExceededException("Transfer limit exceeded by " + excess + PREFERRED_CURRENCY.toString() + ". Limit is " + TRANSFER_LIMIT + PREFERRED_CURRENCY.toString());
        }
    }

}
