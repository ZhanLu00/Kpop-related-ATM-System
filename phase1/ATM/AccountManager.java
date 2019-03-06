package ATM;

import ATM.BankAccounts.BankAccount;

import java.util.ArrayList;
import java.util.List;


public class AccountManager {
    private List<BankAccount> accounts;

    public AccountManager(){
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount getAccount(int id) { return accounts.get(id);}

}
