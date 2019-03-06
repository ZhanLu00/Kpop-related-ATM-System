package ATM;

import ATM.BankAccounts.BankAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * An AccountManager class that stores all accounts.
 */
public class AccountManager {
    private List<BankAccount> accounts;

    public AccountManager() {
        this.accounts = new ArrayList<>();
    }

    /**
     * Adds an account to the list of accounts.
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Takes an id number and returns the BankAccount with corresponding id.
     * If id > accounts.size(), method returns null.
     */
    public BankAccount getAccount(int id) {
        if (id < accounts.size()) {
            return accounts.get(id);
        } else {
            return null;
        }
    }

    /**
     * Transfers money between two accounts.
     * Returns true if the transaction is successful, false otherwise.
     */
    public boolean transfer(double amount, int senderId, int receiverId) {
        BankAccount sender = this.getAccount(senderId);
        BankAccount receiver = this.getAccount(receiverId);
        if(sender.withdraw(amount) && receiver.deposit(amount)) {
            // TODO: 2019-03-05 add transaction date
            Transaction transaction = new Transaction(amount, receiver, null);
            sender.setLastTransaction(transaction);
            return true;
        } else {
            return false;
        }
    }
}
