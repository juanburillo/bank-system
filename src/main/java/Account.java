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

}
