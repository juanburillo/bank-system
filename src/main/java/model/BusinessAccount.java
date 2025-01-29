package model;

import enums.Currency;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

/**
 * Represents a business account. It stores every transaction detail after the operation
 */
public class BusinessAccount extends Account {

    private final static Currency PREFERRED_CURRENCY = Currency.EUR;

    private HashMap<Date, Double> transactionHistory;

    public BusinessAccount(long id, String ownerName, double balance) {
        super(id, ownerName, balance);
        this.transactionHistory = new HashMap<>();
    }

    public HashMap<Date, Double> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(HashMap<Date, Double> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    /**
     * Transfers a certain amount of money from the account to another one and
     * saves it in the transfer history
     * @param amount The amount to be transferred
     * @param account The account to be deposited to
     */
    @Override
    public void transfer(double amount, Account account) {
        super.transfer(amount, account);
        transactionHistory.put(Date.from(Instant.now()), amount);
    }

    /**
     * Prints the transfer history of the account
     */
    public void printTransferHistory() {
        for (Date date : transactionHistory.keySet()) {
            System.out.println(date + " - Transfer of: " + transactionHistory.get(date) + PREFERRED_CURRENCY.toString());
        }
    }

}
