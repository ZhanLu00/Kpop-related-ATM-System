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



    public double getBalance() {
        return this.balance;
    }

    public ATM.Users.Client getClient() {
        return this.client;
    }



    public abstract boolean deposit(double amount);

    public abstract boolean withdraw(double amount);

}