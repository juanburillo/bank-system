package interfaces;

import enums.Currency;
import model.Account;

public interface AccountOperations {

    public void deposit(double amount, Currency currency);
    public void withdraw(double amount, Currency currency);
    public void transfer(double amount, Currency currency, Account account);

}
