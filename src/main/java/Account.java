public class Account {

    private final long id; // Unique bank account identifier
    private long clientId; // ID for the client who owns the account
    private double balance; // Balance of the accounts

    public Account(long id, long clientId, double balance) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
     * @throws IllegalArgumentException Exception thrown if the amount to be deposited is negative or 0
     */
    public void deposit(double amount, Currency currency) throws IllegalArgumentException {
        if (amount >= 0) { // Checks if the amount is positive
            this.balance += amount;
            System.out.println("Successful deposit of " + amount + currency.toString());
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
    }

    /**
     * Withdraws an amount of money of the specified currency from the bank account
     * @param amount The amount of money to be withdrawn
     * @param currency The currency
     * @throws InsufficientFundsException Exception thrown if the amount to be withdrawn is greater than the account balance
     * @throws IllegalArgumentException Exception thrown if the amount to be withdrawn is negative or 0
     */
    public void withdraw(double amount, Currency currency) throws InsufficientFundsException, IllegalArgumentException {
        if (amount >= 0) {
            if (amount <= this.balance) {
                this.balance -= amount;
                System.out.println("Successful withdrawal of " + amount + currency.toString());
            } else {
                throw new InsufficientFundsException("The operation cannot be completed because of insufficient funds.");
            }
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
    }

}
