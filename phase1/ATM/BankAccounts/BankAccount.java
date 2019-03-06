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

    /** Getters **/

    public abstract double getBalance();

    public ATM.Users.Client getClient() {
        return this.client;
    }

    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }

    /**
     * Deposits the given amount into an account.
     */
    public boolean deposit(double amount) {
        this.balance += amount;
        // TODO: 2019-03-05 Add transaction date
        this.lastTransaction = new Transaction(amount, null);
        return true;
    }

    /**
     * Withdraws the given amount out of an account.
     */
    public abstract boolean withdraw(double amount);

}