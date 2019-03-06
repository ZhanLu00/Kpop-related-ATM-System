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
}
