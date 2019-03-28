package ATM;
import ATM.BankAccounts.BankAccount;
import java.util.Date;

/**
 * A Transaction class.
 * A Transaction is either a transaction between accounts or a bill paid to a non-user's account.
 * Withdrawal and deposit does not count as a Transaction.
 */
public class Transaction {

    private final double AMOUNT;
    private final int SENDER;
    private final int RECEIVER;
    // TYPE is one of "transfer", "bill", "withdraw", "deposit"
    private final String TYPE;

    public Transaction(double amount, int sender, int receiver, String type) {
        this.AMOUNT = amount;
        this.SENDER = sender;
        this.RECEIVER = receiver;
        this.TYPE = type;
    }

    public double getAmount() {
        return AMOUNT;
    }

    public int getSender() { return SENDER; }

    public int getReceiver() {
        return RECEIVER;
    }


    public String getType() { return TYPE; }

    @Override
    public String toString() {
        return String.format("[%s] %s -> %s ($%s)", TYPE, SENDER, RECEIVER, AMOUNT);
    }

}
