package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;
import ATM.Transaction;

import java.util.Date;


/**
 * A LineOfCreditAccount class.
 */
public class LineOfCreditAccount extends DebtAccount {

    public LineOfCreditAccount(Date dateCreated, double balance) {
        super(dateCreated, balance);
    }

    public LineOfCreditAccount(Date dateCreated, double balance, Transaction lastTransaction) {
        super(dateCreated, balance,lastTransaction);
    }

    /**
     * Withdraws the given amount out of an account.
     * If the amount after withdrawal is less than the maximum debt that can incur, the withdraw is unsuccessful.
     * The method returns true if withdraw is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (this.balance - amount <= -MAX_DEBT) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

}
