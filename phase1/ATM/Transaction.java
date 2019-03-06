package ATM;
import ATM.BankAccounts.BankAccount;

import java.util.Date;


public class Transaction {

    private final double AMOUNT;
    private final BankAccount RECEIVER;
    private final Date DATE;

    public Transaction(double amount, BankAccount receiver, Date date) {
        this.AMOUNT = amount;
        this.RECEIVER = receiver;
        this.DATE = date;
    }

    /** Getters **/

    public double getAMOUNT() {
        return AMOUNT;
    }

    public Date getDATE() {
        return DATE;
    }

    public BankAccount getReceiver() { return RECEIVER; }

}
