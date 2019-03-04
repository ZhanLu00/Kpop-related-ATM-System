package BankAccounts;

public abstract class BankAccount {
    private static int id;
    private String password;
    protected double balance;
    //    private TimeManager dateCreated;
    private String transaction;
    private Users.Client client;

    protected BankAccount(String password, Users.Client client) {
        this.password = password;
        this.balance = 0;
        // TODO: how to keep track of the most recent transaction?
        this.transaction = ""; // the most recent transaction on this account
        this.client = client;
        id++;
    }

    public int getId() { return id; }

    public double getBalance() {
        return balance;
    }

    public String getTransaction() {
        return transaction;
    }

    public Users.Client getClient() {
        return client;
    }

    // TODO: getDateCreated()

    public boolean setPassword(String initialPassword, String newPassword) {
        if (this.password.equals(initialPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public abstract boolean withdraw(double amount);

    public abstract void deposit(double amount);

    public boolean transfer(double amount, int targetAccount) {
        if (withdraw(amount)) {
            // TODO: search for target account in possibly a list that stores every account? and deposit
            return true;
        }
        return false;
    }

    // TODO: payBill
    // TODO: undo transaction

}
