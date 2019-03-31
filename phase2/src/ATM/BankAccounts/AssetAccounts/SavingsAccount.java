package src.ATM.BankAccounts.AssetAccounts;

import java.util.Date;


/**
 * A SavingsAccount class.
 */
public class SavingsAccount extends AssetAccount {

    public SavingsAccount(Date date, double balance) {
        super(date, balance);
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
        this.balance *= 1.001;
    }

}
