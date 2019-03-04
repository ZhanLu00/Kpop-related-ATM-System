package BankAccounts.DebtAccounts;

import BankAccounts.BankAccount;

public abstract class DebtAccount extends BankAccount {
    DebtAccount(String password, Users.Client client) {
        super(password, client);
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
