package ATM;

import ATM.BankAccounts.BankAccount;

import java.util.ArrayList;


public class AccountManager {
    private ArrayList<BankAccount> accounts;

    public AccountManager(){
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

}
