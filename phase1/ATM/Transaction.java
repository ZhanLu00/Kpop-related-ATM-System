package ATM;
import ATM.BankAccounts.BankAccount;
import java.util.Date;

/**
 * A Transaction class.
 */
public class Transaction {

    private final double AMOUNT;
    private final BankAccount SENDER;
    private final BankAccount RECEIVER;
    private final Date DATE;

    public Transaction(double amount, BankAccount sender, BankAccount receiver, Date date) {
        this.AMOUNT = amount;
        this.SENDER = sender;
        this.RECEIVER = receiver;
        this.DATE = date;
    }

    public double getAmount() {
        return AMOUNT;
    }

    public BankAccount getSender() {
        return SENDER;
    }

    public BankAccount getReceiver() {
        return RECEIVER;
    }

    public Date getDate() {
        return DATE;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s -> %s ($%s)", DATE, SENDER, RECEIVER, AMOUNT);
    }

}
