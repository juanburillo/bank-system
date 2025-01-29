public class Account {

    private long accountNumber;
    private long ownerId;
    private double balance;

    public Account(long accountNumber, long ownerId, double balance) {
        this.accountNumber = accountNumber;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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
     * @throws IllegalArgumentException Exception thrown if the user inputs a negative value or 0
     */
    public void deposit(double amount, Currency currency) throws IllegalArgumentException {
        if (amount >= 0) {
            this.balance += amount;
            System.out.println("Successful deposit of " + amount + currency.toString());
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
    }

}
