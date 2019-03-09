package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;

import java.util.Date;


/**
 * A LineOfCreditAccount class.
 */
public class LineOfCreditAccount extends DebtAccount {

    public LineOfCreditAccount(Date dateCreated, double balance) {
        super(dateCreated, balance);
    }

    /**
     * Withdraws the given amount out of an account.
     * Withdraw is always successful and method always returns true.
     */
    public boolean withdraw(double amount) {
        this.balance += -amount;
        return true;
    }

}
