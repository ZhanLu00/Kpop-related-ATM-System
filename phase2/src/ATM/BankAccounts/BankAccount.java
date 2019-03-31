package ATM.BankAccounts;
import java.io.*;
import java.util.Date;


/**
 * An abstract BankAccount class.
 */
public abstract class BankAccount {

    protected static int nextId = 0;
    protected final int ID;
    protected final Date DATE_CREATED;
    protected double balance = 0;

    public BankAccount(Date dateCreated, double initialBalance) {
        this.ID = nextId;
        BankAccount.nextId += 1;
        this.DATE_CREATED = dateCreated;
        this.balance = initialBalance;
    }

    public int getId() {
        return this.ID;
    }

    public Date getDateCreated() {
        return DATE_CREATED;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getType() {

        if (this instanceof ChequingAccount) {
            return "CHEQUING_ACCOUNT";
        } else if (this instanceof SavingsAccount) {
            return "SAVINGS_ACCOUNT";
        } else if (this instanceof CREDIT)

    }

    public abstract boolean deposit(double amount);
    public abstract boolean withdraw(double amount);

    /**
     * Pays a bill by transferring money out to a non-user's account.
     * Writes the transaction in outgoing.txt.
     * Updates this.lastTransaction.
     * Returns True if paying is successful, false otherwise.
     */
    public boolean payBill(double amount, int receiver) {
        if(this.withdraw(amount)) {
            try {
                String bill = amount + ", " + receiver;
                BufferedWriter writer = new BufferedWriter(new FileWriter("/outgoing.txt"));
                writer.append(bill);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}