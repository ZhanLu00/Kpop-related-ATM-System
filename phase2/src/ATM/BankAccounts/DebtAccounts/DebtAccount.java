package src.ATM.BankAccounts.DebtAccounts;

import src.ATM.BankAccounts.BankAccount;

import java.util.Date;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    private double maxDebt = 420;

    protected DebtAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    public boolean deposit(double amount) {
        this.balance -= amount;
        return true;
    }

    public double getMaxDebt() {
        return this.maxDebt;
    }

    public boolean setMaxDebt(double newMaxDebt) {
        if (newMaxDebt > 0) {
            this.maxDebt = newMaxDebt;
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return this.balance;
    }

}
