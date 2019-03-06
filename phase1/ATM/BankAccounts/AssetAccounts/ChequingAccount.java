package ATM.BankAccounts.AssetAccounts;

import java.util.Date;


/**
 * A ChequingAccount class.
 */
public class ChequingAccount extends AssetAccount {

    public ChequingAccount(ATM.Users.Client client, Date date, double balance) { super(client, date,balance); }

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
