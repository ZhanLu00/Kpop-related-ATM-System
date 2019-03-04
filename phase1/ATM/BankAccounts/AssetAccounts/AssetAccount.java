package ATM.BankAccounts.AssetAccounts;

import ATM.BankAccounts.BankAccount;

public abstract class AssetAccount extends BankAccount {

    AssetAccount(String password, ATM.Users.Client client) {
        super(password, client);
    }

    public void deposit(double amount) {
        this.balance -= amount;
    }

}
