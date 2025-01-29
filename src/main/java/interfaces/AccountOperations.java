package interfaces;

import enums.Currency;
import model.Account;

/**
 * Methods necessary for any bank account class (deposit, withdraw, and transfer)
 */
public interface AccountOperations {

    public void deposit(double amount);
    public void withdraw(double amount);
    public void transfer(double amount, Account account);

}
