package ATM.BankAccounts.AssetAccounts;

import ATM.Transaction;

import java.util.Date;


/**
 * A ChequingAccount class.
 */
public class ChequingAccount extends AssetAccount {

    private boolean primary;

    public ChequingAccount(Date date, double balance, boolean primary) {
        super(date,balance);
        this.primary = primary;
    }

    public boolean getPrimary() { return primary; }

    public void setPrimary(boolean primary) { this.primary = primary; }
    /**
     * Withdraws the given amount out of an account.
     * Withdrawal is successful if balance after withdrawal >= -100.
     * Returns true if withdrawal is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (this.balance - amount >= -100) {
            this.balance += -amount;
            return true;
        } else {
            return false;
        }
    }

}
