package ATM.BankAccounts;
import ATM.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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


    public BankAccount(ATM.Users.Client client, Date date, double balance) {

        this.id = nextId;
        nextId += 1;

        this.client = client;
        this.DATE_CREATED = date;

        this.balance = balance;

    }

    /** Getters **/

    public abstract double getBalance();

    public ATM.Users.Client getClient() {
        return this.client;
    }

    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }

    /** Setters **/

    public void setLastTransaction(Transaction transaction) {
        this.lastTransaction = transaction;
    }

    /**
     * Deposits the given amount into an account.
     */
    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

    /**
     * Withdraws the given amount out of an account.
     */
    public abstract boolean withdraw(double amount);

    /**
     * Undoes the most recent transaction on an account.
     */
    public boolean undoTransaction() {
        double amount = this.lastTransaction.getAMOUNT();
        BankAccount sender = this.lastTransaction.getReceiver();
        // receiver = this
        if(sender.withdraw(amount) && this.deposit(amount)) {
            return true;
            // TODO: what does this.lastTransaction become?
        } else {
            return false;
        }
    }

    public Date getDATE_CREATED() {
        return DATE_CREATED;
    }

    /**
     * Pays a bill by transferring money out to a non-user's account.
     */
    public boolean payBill(double amount, String receiver) {
        if(this.withdraw(amount)) {
            try {
                String bill = amount + ", " + receiver;
                BufferedWriter writer = new BufferedWriter(new FileWriter("outgoing.txt"));
                writer.write(bill);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

}