package BankAccounts.AssetAccounts;

import BankAccounts.BankAccount;

public abstract class AssetAccount extends BankAccount {
    AssetAccount(String password, Users.Client client) {
        super(password, client);
    }

    public void deposit(double amount) {
        this.balance -= amount;
    }
}
