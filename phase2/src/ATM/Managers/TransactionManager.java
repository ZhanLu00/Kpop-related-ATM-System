package ATM.Managers;

import ATM.BankAccounts.BankAccount;
import ATM.Transaction;

import java.util.ArrayList;

/**
 * A TransactionManager class.
 */
public class TransactionManager {

    /** transactions contain every transaction that has been done in the system. **/
    private ArrayList<Transaction> transactions;

    public TransactionManager(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Add a new transaction to transactions by the amount, sender, receive, and type.
     */
    public void addTransaction(double amount, int sender, int receiver, String type) {
        transactions.add(new Transaction(amount, sender, receiver, type));
    }

    /**
     * Add a new transaction to transactions.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Returns a list of transactions sent by an account.
     */
    public ArrayList<Transaction> getTransactionsBySender(int sender) {
        ArrayList<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getSender() == sender) {
                result.add(transaction);
            }
        }
        return result;
    }

    /**
     * Returns a list of transactions received by an account.
     */
    public ArrayList<Transaction> getTransactionsByReceiver(int receiver) {
        ArrayList<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getReceiver() == receiver) {
                result.add(transaction);
            }
        }
        return result;
    }

    /**
     * Undoes the transaction at a given index.
     * The transaction undone will be removed from transactions.
     */
    // FIXME CAN'T UNDO BILLS?
    public boolean undoTransaction(int id, AccountManager accountManager) {
        if (id < 0 || id >= transactions.size()) {
            return false;
        }

        Transaction transaction = transactions.get(id);

        double amount = transaction.getAmount();

        BankAccount sender = accountManager.getAccount(transaction.getSender());
        BankAccount receiver = accountManager.getAccount(transaction.getReceiver());


        if (receiver.withdraw(amount) && sender.deposit(amount)) {
            transactions.remove(id);
            return true;
        }
        return false;
    }

    /** Getter **/
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
