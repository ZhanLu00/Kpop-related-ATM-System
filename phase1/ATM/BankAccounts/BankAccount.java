package ATM.BankAccounts;
import ATM.Transaction;

import java.util.Date;


/**
 * An abstract BankAccount class.
 */
public abstract class BankAccount {

    protected static int nextId = 0;

    protected int id;
    protected ATM.Users.Client client;
    protected final Date DATE_CREATED;

    protected double balance = 0;
    protected Transaction lastTransaction;


    public BankAccount(ATM.Users.Client client, Date date) {

        this.id = nextId;
        nextId += 1;

        this.client = client;
        this.DATE_CREATED = date;

    }


    public abstract double getBalance();

    public ATM.Users.Client getClient() {
        return this.client;
    }

    public boolean deposit(double amount) {
        this.balance += amount;
        this.lastTransaction = new Transaction();
        return true;
    }

    public abstract boolean withdraw(double amount);

    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }

}