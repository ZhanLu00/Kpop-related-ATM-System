package ATM.BankAccounts.AssetAccounts;

import ATM.BankAccounts.BankAccount;

import java.util.Date;

public class LotteryAccount extends AssetAccount {
    public LotteryAccount(Date date, double balance) {
        super(date, balance);
    }

    /**
     * Deposits the given amount into an account.
     */
    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

    /**
     * Withdraws the given amount out of an account.
     * Withdrawal is successful if balance >= amount.
     * Returns true if withdrawal is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance += -amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increases a savings account balance by a factor of 0.1%
     */
    public void collectInterest() {
        this.balance *= 0.501 + new java.util.Random().nextDouble();
    }


}