package ATM.BankAccounts.DebtAccounts;
import java.util.Date;


/**
 * A LineOfCreditAccount class.
 */
public class LineOfCreditAccount extends DebtAccount {

    public LineOfCreditAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Attempts to withdraw *amount* from account.
     * Succeeds if balance after withdrawal <= maxDebt.
     */
    public boolean withdraw(double amount) {
        if (this.balance + amount <= this.getMaxDebt()) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

}
