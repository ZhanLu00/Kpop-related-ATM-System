package ATM;

import ATM.Transaction;

import java.util.ArrayList;

public class TransactionManager {

    private ArrayList<Transaction> transactions;

    public TransactionManager(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(double amount, int sender, int receiver, String type) {
        transactions.add(new Transaction(amount, sender, receiver, type));
    }

    public ArrayList<Transaction> getTransactionsBySender(int sender) {
        ArrayList<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getSender() == sender) {
                result.add(transaction);
            }
        }
        return result;
    }

    public ArrayList<Transaction> getTransactionsByReceiver(int receiver) {
        ArrayList<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getReceiver() == receiver) {
                result.add(transaction);
            }
        }
        return result;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
