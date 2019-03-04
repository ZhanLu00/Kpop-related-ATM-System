package ATM.BankAccounts;


/**
 * An abstract BankAccount class.
 */
public abstract class BankAccount {

    protected static int nextId = 0;

    protected int id;
    protected double balance = 0;
    protected ATM.Users.Client client;


    public BankAccount(ATM.Users.Client client) {

        this.id = nextId;
        nextId += 1;

        this.client = client;

    }


    public abstract double getBalance();

    public ATM.Users.Client getClient() {
        return this.client;
    }

    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

    public abstract boolean withdraw(double amount);

}