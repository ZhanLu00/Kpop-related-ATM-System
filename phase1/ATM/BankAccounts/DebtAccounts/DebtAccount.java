package ATM.BankAccounts.DebtAccounts;

import ATM.BankAccounts.BankAccount;

public abstract class DebtAccount extends BankAccount {
    DebtAccount(String password, ATM.Users.Client client) {
        super(password, client);
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
